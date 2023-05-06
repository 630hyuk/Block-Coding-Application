import json
import boto3
from datetime import datetime

def handler(event, context):

    debug_email(event)
    # 새로운 사용자의 정보 추출
    nickname = event['name']
    email = event['request']['userAttributes']['email']

    # format : 2023-04-23T09:16:20.734Z
    timestamp = datetime.now().strftime('%Y-%m-%dT%H:%M%S.000Z')
    # DynamoDB 테이블에 새로운 데이터 추가
    dynamodb = boto3.resource('dynamodb')
    table = dynamodb.Table('UserData-764tmacrt5dobnsevpovqnmw4u-staging')
    table.put_item(Item={
        'id': nickname,
        'email': email,
        'nickname': nickname,
        'registerStamp': timestamp
    })

    debug_email('succeed!!')
    return event

def debug_email(event):
   message = str(event)
   sns = boto3.client('sns')
   sns.publish(TopicArn="arn:aws:sns:ap-northeast-2:582924785841:EmailToMe",
   Subject="EmailToMe", Message = message)

   return 'ok'