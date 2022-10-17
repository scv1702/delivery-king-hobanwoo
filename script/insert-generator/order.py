import csv

order_f = open('../csv/order.csv', 'r', encoding='utf-8')
order_insert_f = open('../../sql/insert/order_insert.sql', 'w', encoding='utf-8')
order_rdr = csv.reader(order_f, delimiter='|')

for order in order_rdr:
    Order_ID, User_ID, Store_ID, Payment, State, Order_Date = order
    order_insert_f.write(f'insert into order values({Order_ID}, {User_ID}, {Store_ID}, \"{Payment}\", \"{State}\", to_date(\"{Order_Date}\", \"yyyy-mm-dd hh24:mi:ss\"));\n')