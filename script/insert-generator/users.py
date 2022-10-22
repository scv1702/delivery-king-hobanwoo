import csv

user_f = open('csv/user.csv', 'r', encoding='utf-8')
order_f = open('csv/order.csv', 'r', encoding='utf-8')
user_insert_f = open('sql/insert/users_insert.sql', 'w', encoding='utf-8')
user_rdr = csv.reader(user_f, delimiter='|')
order_rdr = csv.reader(order_f, delimiter='|')

order_cnt = [0] * 51

for order in order_rdr:
    Order_ID, User_ID, Store_ID, Payment, State, Order_Date = order
    order_cnt[int(User_ID)] += 1

for user in user_rdr:
    user_id, username, password, phone_number, dname = user
    if order_cnt[int(user_id)] <= 1:
        membership_tier = "고마워요"
    elif 2 <= order_cnt[int(user_id)] <= 3:
        membership_tier = "최고예요"
    elif order_cnt[int(user_id)] >= 4:
        membership_tier = "사랑해요"
    user_insert_f.write(f'insert into users values({user_id}, \'{username}\', \'{dname}\', \'{password}\', \'{phone_number}\', \'{membership_tier}\');\n')
 