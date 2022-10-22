import csv

review_f = open('csv/review.csv', 'r', encoding='utf-8')
review_insert_f = open('sql/insert/review_insert.sql', 'w', encoding='utf-8')
review_rdr = csv.reader(review_f, delimiter='|')

for review in review_rdr:
    Review_ID, User_ID, Store_ID, Star_Rating, Comment, Created_At = review
    review_insert_f.write(f'insert into review values({Review_ID}, {User_ID}, {Store_ID}, {Star_Rating}, \'{Comment}\', to_date(\'{Created_At}\', \'yyyy-mm-dd hh24:mi:ss\'));\n')