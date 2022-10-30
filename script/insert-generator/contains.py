import csv
import random

contains_f = open('csv/contains.csv', 'r', encoding='utf-8')
contains_insert_f = open('sql/insert/contains_insert.sql', 'w', encoding='utf-8')
contains_rdr = csv.reader(contains_f, delimiter='|')

order_menu_id = 0
prev_order_id  = -1
for contains in contains_rdr:
    # 1|1|특등심카츠|NULL|1-1.jpg|18000
    _, review_id = contains
    if review_id != prev_order_id:
        order_menu_id = 1
    else:
        order_menu_id += 1
    contains_insert_f.write(f'insert into contains values({order_menu_id}, {review_id}, {review_id});\n')
    prev_order_id = review_id