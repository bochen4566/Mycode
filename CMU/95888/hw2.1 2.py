# Homework 2
# @author: Dragon Xu, Bochen Wang (Team 15)
# Date: 09/01/2023

# Set input file path and output file name
input_file = 'D:\CMU\95888 Data Focused Python\Homework 2\cme.20210709.c.pa2'
output_file = 'CL_expirations_and_settlements.txt'
# Read input file
with open(input_file, 'r', encoding='utf-8') as f_in, open(output_file, 'w', encoding='utf-8') as f_out:
    # Write the column headers of the first table
    f_out.write('Futures   Contract    Contract    Futures     Options    Options\n')
    f_out.write('Code      Month       Type        Exp Date    Code       Exp Date\n')
    f_out.write('-------   --------    --------    --------    -------    --------\n')
    # Initialize a single-space string
    space = ' '
    # Initialize a single-dash string
    dash = '-'
    # Read input file line-by-line
    for line in f_in:
        # Get all the type B records
        if line[0] == 'B':
            # Get only records between 2021-09 and 2023-12
            if int(line[18:24]) in range(202109, 202313):
                # Record for Crude Oil(CL)
                if line[5:8] == 'CL ':
                    f_out.write('CL' + space*8 + line[18:22] + dash + line[22:24] + space*5 + line[15:18].capitalize()
                                + space*9 + line[91:95] + dash + line[95:97] + dash + line[97:99] + '\n')
                # Record for WTI Crude(LO)
                elif line[5:8] == 'LO ':
                    f_out.write('CL' + space*8 + line[18:22] + dash + line[22:24] + space*5 + line[15:18].capitalize() +
                                space*21 + 'LO' + space*9 + line[91:95] + dash + line[95:97] + dash + line[97:99] + '\n')

    # Write the column headers of the second table
    f_out.write('Futures   Contract    Contract    Strike    Settlement\n')
    f_out.write('Code      Month       Type        Price     Price     \n')
    f_out.write('-------   --------    --------    ------    ----------\n')
    # Create a function to compute settlement price
    def get_price(x, d):
        # @param x  a string of numbers that contains price information
        # @param d  correct divisor
        # Initialize the starting position of price number to be 0
        start = 0
        for i in range(len(x)):
            if x[i, i + 1] != '0':
                # Store the starting position of the settlement price
                start = i
                # Get out of the loop once we encounter the first non-zero character
                break
        # Compute the settlement price (WTI crude futures price has 2 digits left to the decimal point)
        result = int(x[start:]) / float(d)
        return result
    # Read input file line-by-line
    for line in f_in:
        # Get all the type 81 records
        if line[0:2] == '81':
            # Get only records between 2021-09 and 2023-12 for Crude Oil(CL)
            if (int(line[18:24]) in range(202109, 202313)) and (line[5:8] == 'CL '):
                if line[25:28] == 'FUT':
                    # Compute the settlement price which is 14 digits long(109 ~ 122)
                    settlement_price = get_price(line[108:122], 100)
                    # Print each line to the output file
                    f_out.write('CL' + space*8 + line[29:33] + dash + line[33:35] + space*5 + 'Fut' +
                                space*(27 - len(str(settlement_price))) + f"{settlement_price:.2f}")
                elif (line[28:29] == 'C') or (line[28:29] == 'P'):
                    # Determine the call option (Option Right Code)
                    option_code = 'Call'
                    if line[28:29] == 'P':
                        option_code = 'Put'
                    # Compute the settlement price which is 14 digits long(109 ~ 122)
                    settlement_price = get_price(line[108:122], 10)
                    # Compute the strike price which is 7 digits long(48 ~ 54)
                    strike_price = get_price(line[47:54], 1000)
                    # Print each line to the output file
                    f_out.write('CL' + space*8 + line[29:33] + dash + line[33:35] + space*5 + option_code +
                                space*(17 - len(option_code) - len(str(strike_price))) + f"{strike_price:.2f}" +
                                space*(13 - len(str(settlement_price))) + f"{settlement_price:.2f}")

