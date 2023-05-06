import json
import boto3

def lambda_handler(event, context):
    # 새로운 사용자의 정보 추출
    nickname = event['name']
    email = event['request']['userAttributes']['email']

    # DynamoDB 테이블에 새로운 데이터 추가
    dynamodb = boto3.resource('dynamodb')
    table = dynamodb.Table('UserData-764tmacrt5dobnsevpovqnmw4u-staging')
    table.put_item(Item={
        'nickname': nickname,
        'email': email
    })

    return event