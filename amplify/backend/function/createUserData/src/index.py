import json
import boto3
from datetime import datetime

'''
example of payload
{
    'version': '1', 
    'region': 'ap-northeast-2', 
    'userPoolId': 'ap-northeast-2_jec1sIwqC', 
    'userName': 'Pumpkinjam', 
    'callerContext': {
        'awsSdkVersion': 'aws-sdk-android-2.7.4', 
        'clientId': '71r9rffgpecdfvuutoi6fta41j'}, 
        'triggerSource': 'PostAuthentication_Authentication', 
        'request': {
            'userAttributes': {
                'sub': '1ea94352-985f-4c45-b6cd-90c6a4058631', 
                'cognito:email_alias': 'rnjsl1212@naver.com', 
                'cognito:user_status': 'CONFIRMED', 
                'email_verified': 'true', 
                'name': 'Pumpkinjam', 
                'email': 'rnjsl1212@naver.com'
            }, 
            'newDeviceUsed': False
        }, 
        'response': {}
}
'''
def handler(event, context):

    debug_email(event)
    # 새로운 사용자의 정보 추출
    nickname = event['request']['userAttributes']['name']
    email = event['request']['userAttributes']['email']

    # format : 2023-04-23T09:16:20.734Z
    timestamp = datetime.now().strftime('%Y-%m-%dT%H:%M:%S.000Z')
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