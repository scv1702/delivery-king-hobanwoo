import csv

department_f = open('csv/department.csv', 'r', encoding='utf-8')
department_insert_f = open('sql/insert/department_insert.sql', 'w', encoding='utf-8')
department_rdr = csv.reader(department_f, delimiter='|')

for department in department_rdr:
    department_insert_f.write(f'insert into department values(\'{department[0]}\');\n')