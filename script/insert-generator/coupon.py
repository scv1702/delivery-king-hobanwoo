import csv

coupon_f = open('csv/coupon.csv', 'r', encoding='utf-8')
coupon_insert_f = open('sql/insert/coupon_insert.sql', 'w', encoding='utf-8')
coupon_rdr = csv.reader(coupon_f, delimiter='|')

for coupon in coupon_rdr:
    Coupon_ID, User_ID, Discount_Amount, Expiration_Date, Minimum_Order_Amount, State = coupon
    coupon_insert_f.write(f'insert into coupon values({Coupon_ID}, {User_ID}, {Discount_Amount}, to_date(\'{Expiration_Date}\', \'yyyy-mm-dd hh24:mi:ss\'), {Minimum_Order_Amount}, \'{State}\');\n')