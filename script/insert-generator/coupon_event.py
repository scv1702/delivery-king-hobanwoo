import csv

coupon_f = open('csv/coupon_event.csv', 'r', encoding='utf-8')
coupon_insert_f = open('sql/coupon_event_insert.sql', 'w', encoding='utf-8')
coupon_rdr = csv.reader(coupon_f, delimiter='|')

for coupon in coupon_rdr:
    COUPON_EVENT_ID, QUANTITY, DESCRIPTION, Discount_Amount, Expiration_Date, Minimum_Order_Amount = coupon
    coupon_insert_f.write(f'insert into coupon_event values({COUPON_EVENT_ID}, {QUANTITY}, \'{DESCRIPTION}\', {Discount_Amount}, to_date(\'{Expiration_Date}\', \'yyyy-mm-dd\'), {Minimum_Order_Amount});\n')