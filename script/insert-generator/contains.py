import csv
import random

contains_f = open('csv/contains.csv', 'r', encoding='utf-8')
contains_insert_f = open('sql/insert/contains_insert.sql', 'w', encoding='utf-8')
contains_rdr = csv.reader(contains_f, delimiter='|')

for contains in contains_rdr:
    # 1|1|특등심카츠|NULL|1-1.jpg|18000
    order_menu_id, review_id = contains
    contains_insert_f.write(f'insert into contains values({order_menu_id}, {review_id});\n')