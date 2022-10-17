import csv

user_address_f = open('csv/user_address.csv', 'r', encoding='utf-8')
user_address_insert_f = open('sql/insert/user_address_insert.sql', 'w', encoding='utf-8')
user_address_rdr = csv.reader(user_address_f, delimiter='|')

for user_address in user_address_rdr:
    User_ID, UAddress = user_address
    user_address_insert_f.write(f'insert into user_address values({User_ID}, \"{UAddress}\");\n')