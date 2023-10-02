#group member: Bochen Wang

records = []

with open('expenses.txt', 'r') as file:
    records = [line.strip() for line in file]



import re
# 1a
# pat = r'D'

#1b
#pat = r'\''

#1c
#pat = r'\"'
# for line in records:
#     if re.search(pat, line) != None:
#         print(line)

#1d
#pat = r'^7' # starts with

#1e
#pat = r'[rt]$'

#1f
#pat = r'\.'

#1g
#pat = r'r.*g'

#1h
#pat = r'[A-Z]{2}'

#1i
#pat = r'\,'

#1j
#pat = r'(,){3,}'

#1k
#pat= r'^[^vwxyz]+$' 

#1l
#pat = r'\b(?:[1-9][0-9]\.[0-9][0-9])\b'

#1m
#pat = r'([^,]*,[^,]*){3}'

#1n
#pat = r'\('

#1o
#pat = r'^.{7}meal'

#1p
#pat = r'^[^:]+:([^:]{4}):'

#1q
#pat = r'\d{4}03\d{2}'

#1r
#pat = r'a.*b.*c'

#1s
pat = r'(..).*\1.*\1'

#1t
#pat = r'\d{8}(a.*\d|\d.*a)'

#1u
#pat = r'^[^A-Z]*$'

#1v
#pat = r'(di|d.*i)'

for line in records:
    if re.search(pat, line) != None:
        print(line)


