import csv
import random

order_menu_f = open('csv/order_menu.csv', 'r', encoding='utf-8')
order_menu_insert_f = open('sql/insert/order_menu_insert.sql', 'w', encoding='utf-8')
order_menu_rdr = csv.reader(order_menu_f, delimiter='|')

for order_menu in order_menu_rdr:
    # 1|1|짜장면|NULL|4000|1
    order_menu_id, order_id, menu_name, image, price, quantity = order_menu
    if image == "NULL":
        order_menu_insert_f.write(f'insert into order_menu values({order_menu_id}, {order_id}, \"{menu_name}\", NULL, {price}, {quantity});\n')
    else:
        order_menu_insert_f.write(f'insert into order_menu values({order_menu_id}, {order_id}, \"{menu_name}\", \"{image}\", {price}, {quantity});\n')