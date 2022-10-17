import csv
import random

menu_f = open('csv/menu.csv', 'r', encoding='utf-8')
menu_insert_f = open('sql/insert/menu_insert.sql', 'w', encoding='utf-8')
menu_rdr = csv.reader(menu_f, delimiter='|')

for menu in menu_rdr:
    # 1|1|특등심카츠|NULL|1-1.jpg|18000
    menu_id, store_id, menu_name, description, image, price = menu

    if description == "NULL":
        if image == "NULL":
            menu_insert_f.write(f'insert into menu values({menu_id}, {store_id}, \"{menu_name}\", NULL, NULL, {price});\n')
        else:
            menu_insert_f.write(f'insert into menu values({menu_id}, {store_id}, \"{menu_name}\", NULL, \"{image}\", {price});\n')
    else:
        if image == "NULL":
            menu_insert_f.write(f'insert into menu values({menu_id}, {store_id}, \"{menu_name}\", \"{description}\", \"{image}\", {price});\n')
        else:
            menu_insert_f.write(f'insert into menu values({menu_id}, {store_id}, \"{menu_name}\", \"{description}\", NULL, {price});\n')