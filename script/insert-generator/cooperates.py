import csv
import random

cooperates_f = open('csv/cooperates.csv', 'r', encoding='utf-8')
cooperates_insert_f = open('sql/insert/cooperates_insert.sql', 'w', encoding='utf-8')
cooperates_rdr = csv.reader(cooperates_f, delimiter='|')

for cooperates in cooperates_rdr:
    # 1|간호대학
    store_id, dname = cooperates
    cooperates_insert_f.write(f'insert into cooperates values({store_id}, \'{dname}\');\n')