package com.spring.project.dynomodb.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "student")
public class Student {
	
	@DynamoDBHashKey
	@DynamoDBAutoGeneratedKey
	private String sid;
	@DynamoDBAttribute
	private String sname;
	@DynamoDBAttribute
	private String email;
	@DynamoDBAttribute
	private String mobile;
	
	

}
