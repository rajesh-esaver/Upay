import logging

import azure.functions as func

import pyodbc
import json

server = 'upay.database.windows.net'
database = 'upay'
username = 'devenger'
password = 'hackathon@01'
driver= '{ODBC Driver 17 for SQL Server}'
cnxn = pyodbc.connect('DRIVER='+driver+';SERVER='+server+';PORT=1433;DATABASE='+database+';UID='+username+';PWD='+ password)
cursor = cnxn.cursor()

def get_last_user_id():
    cursor.execute("SELECT TOP 1 id FROM [dbo].[users] order by id desc")
    row = cursor.fetchone()
    number = 10
    while row:
        number = int(row[0])
        break
    return number

def get_last_shop_id():
    cursor.execute("SELECT TOP 1 id FROM [dbo].[shops] order by id desc")
    row = cursor.fetchone()
    number = 10
    while row:
        number = int(row[0])
        break
    return number

def get_last_transaction_id():
    cursor.execute("SELECT TOP 1 id FROM [dbo].[transactions] order by id desc")
    row = cursor.fetchone()
    number = 10
    while row:
        number = int(row[0])
        break
    return number

def get_last_bill_row_id():
    cursor.execute("SELECT TOP 1 id FROM [dbo].[bills] order by id desc")
    row = cursor.fetchone()
    number = 10
    while row:
        number = row[0]
        break
    return number

def get_last_bill_id():
    cursor.execute("SELECT TOP 1 bill_id FROM [dbo].[bills] order by id desc")
    row = cursor.fetchone()
    number = 10
    while row:
        number = row[0]
        break
    return number

def get_last_utilized_amount(uid):
    cursor.execute("SELECT utilized_amount FROM [dbo].[bank_accounts] where user_id = "+str(uid)+"")
    row = cursor.fetchone()
    amount = 0
    while row:
        amount = int(row[0])
        break
    return amount

def get_profile(uid):
    cursor.execute("SELECT * FROM [dbo].[users] where id = "+str(uid)+"")
    row = cursor.fetchone()
    dict_user = {}
    while row:
        dict_user["id"] = row[0]
        dict_user["name"] = row[1]
        dict_user["mobile_no"] = row[2]
        dict_user["email_id"] = row[3]
        dict_user["address"] = row[4]
        dict_user["finger_id"] = row[5]
        dict_user["dp_id"] = row[6]
        row = cursor.fetchone()
    return json.dumps(dict_user)

def get_transactions(uid):
    cursor.execute("SELECT *,sp.shop_name FROM [dbo].[transactions] tr inner join [dbo].[shops] sp on tr.id = sp.id where tr.user_id = "+str(uid)+"")
    row = cursor.fetchone()
    dict_val = {}
    while row:
        dict_val["id"] = row[0]
        dict_val["bill_id"] = row[1]
        dict_val["total_amount"] = row[2]
        dict_val["shop_id"] = row[3]
        dict_val["user_id"] = row[4]
        dict_val["timestamp"] = row[5]
        dict_val["shop_name"] = row[6]
        row = cursor.fetchone()
    return json.dumps(dict_val)

def get_bank(uid):
    cursor.execute("SELECT * FROM [dbo].[bank_accounts] where user_id = "+str(uid)+"")
    row = cursor.fetchone()
    dict_val = {}
    while row:
        dict_val["id"] = row[0]
        dict_val["account_number"] = row[1]
        dict_val["limit_value"] = row[2]
        dict_val["utilized_amount"] = row[3]
        dict_val["account_type"] = row[4]
        dict_val["user_id"] = row[5]
        row = cursor.fetchone()
    return json.dumps(dict_val)

def get_shop(shop_id):
    cursor.execute("SELECT * FROM [dbo].[shops] where id = "+str(shop_id)+"")
    row = cursor.fetchone()
    dict_val = {}
    while row:
        dict_val["id"] = row[0]
        dict_val["shop_name"] = row[1]
        dict_val["mobile_no"] = row[2]
        dict_val["address"] = row[3]
        dict_val["finger_device_id"] = row[4]
        row = cursor.fetchone()
    return json.dumps(dict_val)

def make_purchase(uid, shop_id, amount):
    row_id = get_last_bill_row_id()
    row_id += 1

    bill_id = get_last_bill_id()
    bill_id += 1

    # updating billing
    cursor.execute("INSERT into [dbo].[bills] (id, bill_id, product_name, quantity, price, amount) values (?, ?, ?, ?, ?, ?)", row_id, bill_id, 'Product X', '1', str(amount), str(amount))
    cnxn.commit()

    # updating transaction
    tran_id = get_last_transaction_id()
    tran_id += 1
    cursor.execute("INSERT into [dbo].[transactions] (id, bill_id, total_amount, shop_id, user_id, timestamp) values (?, ?, ?, ?, ?, ?)", tran_id, bill_id, str(amount), shop_id, uid, str(1575742468))    
    cnxn.commit()

    # updating bank
    utilized_amount = get_last_utilized_amount(uid)
    utilized_amount += int(amount)
    cursor.execute("UPDATE [dbo].[bank_accounts] set utilized_amount = "+str(utilized_amount)+" where user_id = "+str(uid)+"")
    cnxn.commit()

def clear_due(uid):
    number = 0
    cursor.execute("UPDATE [dbo].[bank_accounts] set utilized_amount = "+str(number)+" where user_id = "+str(uid)+"")
    cnxn.commit()    

def main(req: func.HttpRequest) -> func.HttpResponse:
    logging.info('Python HTTP trigger function processed a request.')

    keys = ["get_transactions", "get_profile", "get_bank", "get_shop", "make_purchase", "clear_due"]

    uid = req.params.get('uid')
    shop_id = req.params.get('shop_id')
    key_name = req.params.get('key_name')
    amount = req.params.get('amount')
    
    if(key_name == keys[0]):
        return func.HttpResponse(get_transactions(uid))
    elif(key_name == keys[1]):
        return func.HttpResponse(get_profile(uid))
    elif(key_name == keys[2]):
        return func.HttpResponse(get_bank(uid))
    elif(key_name == keys[3]):
        return func.HttpResponse(get_shop(shop_id))
    elif(key_name == keys[4]):
        return func.HttpResponse(make_purchase(uid, shop_id, amount))
    elif(key_name == keys[5]):
        return func.HttpResponse(clear_due(uid))
    else:
        return func.HttpResponse(
             "Please pass a correct key name",
             status_code=400
        )
