import csv
import random

stores_f = open('csv/stores.csv', 'r', encoding='utf-8')
stores_insert_f = open('sql/insert/store_insert.sql', 'w', encoding='utf-8')
stores_rdr = csv.reader(stores_f, delimiter='|')

delivery_fees = [ 1000, 2000, 3000 ]
business_hours = [ 17, 18, 19, 20, 21, 22, 23, 24 ]
for stores in stores_rdr:
    stores_id, address, category, phone_number, store_name, description = stores
    delivery_fee = random.choice(delivery_fees)
    image = f'{stores_id}.jpeg'
    business_hour = random.choice(business_hours)

    if description == "NULL":
        stores_insert_f.write(f'insert into store values({stores_id}, \'{address}\', \'{category}\', \'{store_name}\', \'{phone_number}\', NULL, {delivery_fee}, \'{image}\', {business_hour});\n')
    else:
        stores_insert_f.write(f'insert into store values({stores_id}, \'{address}\', \'{category}\', \'{store_name}\', \'{phone_number}\', \'{description}\', {delivery_fee}, \'{image}\', {business_hour});\n')