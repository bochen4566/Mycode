#Team 14
#Bochen Wang
#Chloe Wang

# 1.a
daily_yield_curves_2019_0 = []
daily_yield_curves_2022_0 = []
daily_yield_curves_2022 = []

with open("daily-treasury-rates_2019.csv", 'r') as file:
    next(file)
    for line in file:
        m = line.split(',')
        for i in range(1,len(m)):
            m[i]=float(m[i])
        daily_yield_curves_2019_0.append(m)

with open("daily-treasury-rates_2022.csv", 'r') as file1, open("daily_yield_curves_2022.txt", 'w') as o:
    next(file1)
    for line in file1:
        m = line.split(',') #split the lines and make it to the sepearate line
        tmp = [m[0]]
        for i in range(1,len(m)):
            if i != 4:
                m[i]=float(m[i])
                tmp.append(m[i])
        daily_yield_curves_2022_0.append(tmp)
    
    o.write('Date        '+'1 mo  '+'2 mo  '+'3 mo  '+'6 mo  '+'1 yr  '+'2 yr  '+
            '3 yr  '+'5 yr  '+ '7 yr  '+'10 yr  '+'20 yr  '+'30 yr  \n')

daily_yield_curves_2022.append(['Date', '1 mo', '2 mo', '3 mo', '6 mo', '1 yr', '2 yr',
            '3 yr', '5 yr', '7 yr', '10 yr', '20 yr', '30 yr'])

with open("daily_yield_curves_2022.txt", 'a') as o:
    w = "  "
    for i in range(1, len(daily_yield_curves_2022_0)+1):
        daily_yield_curves_2022.append(daily_yield_curves_2022_0[0-i])
        o.write(daily_yield_curves_2022_0[0-i][0] + w +
                "{:.2f}".format(daily_yield_curves_2022_0[0-i][1]) + w +
                "{:.2f}".format(daily_yield_curves_2022_0[0-i][2]) + w +
                "{:.2f}".format(daily_yield_curves_2022_0[0-i][3]) + w +
                "{:.2f}".format(daily_yield_curves_2022_0[0-i][4]) + w +
                "{:.2f}".format(daily_yield_curves_2022_0[0-i][5]) + w +
                "{:.2f}".format(daily_yield_curves_2022_0[0-i][6]) + w +
                "{:.2f}".format(daily_yield_curves_2022_0[0-i][7]) + w +
                "{:.2f}".format(daily_yield_curves_2022_0[0-i][8]) + w +
                "{:.2f}".format(daily_yield_curves_2022_0[0-i][9]) + w +
                "{:.2f}".format(daily_yield_curves_2022_0[0-i][10]) + w +
                "{:.2f}".format(daily_yield_curves_2022_0[0-i][11]) + w +
                "{:.2f}".format(daily_yield_curves_2022_0[0-i][12]) + w +'\n'
                )
        
daily_yield_curves_2019 = []

daily_yield_curves_2019.append(['Date', '1 mo', '2 mo', '3 mo', '6 mo', '1 yr', '2 yr',
'3 yr', '5 yr', '7 yr', '10 yr', '20 yr', '30 yr'])
for i in range(1, len(daily_yield_curves_2019_0)-1):
    daily_yield_curves_2019.insert(i, daily_yield_curves_2019_0[0-i])

# 1.b
import matplotlib.pyplot as plt
import numpy as np

# Remove the header
data_without_header = daily_yield_curves_2022[1:]
rates = [row[1:] for row in data_without_header]
rates = np.array(rates)

X = np.arange(0, len(data_without_header))
Y = [1, 2, 3, 6, 12, 24, 36, 60, 84, 120, 240, 360]
X, Y = np.meshgrid(X, Y)
Z = rates.T 

fig = plt.figure(figsize=(12, 6))

# 3D Surface Plot
ax1 = fig.add_subplot(121, projection='3d')
surf = ax1.plot_surface(X, Y, Z, cmap='viridis')
ax1.set_xlabel('Trading days since 01/02/22')
ax1.set_ylabel('Months to maturity')
ax1.set_zlabel('Rate')
ax1.set_title('3D Surface Plot')

# Set the axis limits (@TODO: what's the requirement for the axis limits?)
# Our axis limits are slightly different from the example in the homework as our data would exceed the range
ax1.set_xlim(0, 250)
ax1.set_ylim(0, 350)
ax1.set_zlim(0.0, 4.5)

# Add colorbar for the surface plot to act as a legend for rate values
fig.colorbar(surf, ax=ax1, shrink=0.6, aspect=10, label='Rate')

# 3D Wireframe Plot
ax2 = fig.add_subplot(122, projection='3d')
ax2.plot_wireframe(X, Y, Z, color='black')
ax2.set_xlabel('Trading days since 01/02/22')
ax2.set_ylabel('Months to maturity')
ax2.set_zlabel('Rate')
ax2.set_title('Wireframe Plot')

# Set the axis limits for the wireframe plot
ax2.set_xlim(0, 250)
ax2.set_ylim(0, 350)
ax2.set_zlim(0.0, 4.5)

plt.tight_layout()
plt.show()



# Problem c
import pandas as pd

header = daily_yield_curves_2022[0][1:]  # Maturity labels
dates = [row[0] for row in daily_yield_curves_2022[1:]]
data = [row[1:] for row in daily_yield_curves_2022[1:]]

yield_curve_df = pd.DataFrame(data, columns=header, index=dates)

yield_curve_df.plot(figsize=(10,6))
plt.title("Interest Rates Time Series, 2022")
plt.ylabel("Interest Rate")
plt.xlabel("Trading Date")
plt.legend(title="Maturity")
plt.grid(True)
plt.show()

by_day_yield_curve_df = yield_curve_df.transpose().iloc[:, ::20]

mapping = {
    '1 mo': 1, '2 mo': 2, '3 mo': 3, '6 mo': 6, 
    '1 yr': 12, '2 yr': 24, '3 yr': 36, '5 yr': 60, 
    '7 yr': 84, '10 yr': 120, '20 yr': 240, '30 yr': 360
}
by_day_yield_curve_df = by_day_yield_curve_df.rename(index=mapping)

by_day_yield_curve_df.plot(figsize=(10,6))
plt.title("2022 Yield Curves, 20 Day Intervals")
plt.ylabel("Interest Rate")
plt.xlabel("Months to Maturity")
plt.legend(title="Trading Date", bbox_to_anchor=(1.05, 1), loc='upper left')
plt.grid(True)
plt.tight_layout()
plt.show()