import csv

stores_f = open('../csv/stores.csv', 'r', encoding='utf-8')
stores_insert_f = open('../../sql/insert/stores_insert.sql', 'w', encoding='utf-8')
stores_rdr = csv.reader(stores_f, delimiter='|')

for stores in stores_rdr:
    stores_id, Address, category, phone_number, dname = stores
    stores_insert_f.write(f'insert into stores values({stores_id}, \"{storesname}\", \"{dname}\", \"{password}\", \"{phone_number}\");\n')