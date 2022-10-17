import csv

user_f = open('csv/user.csv', 'r', encoding='utf-8')
user_insert_f = open('sql/insert/user_insert.sql', 'w', encoding='utf-8')
user_rdr = csv.reader(user_f, delimiter='|')

for user in user_rdr:
    user_id, username, password, phone_number, dname = user
    user_insert_f.write(f'insert into user values({user_id}, \"{username}\", \"{dname}\", \"{password}\", \"{phone_number}\");\n')