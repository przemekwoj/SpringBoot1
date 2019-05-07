package com.przemo.RestAPI.aspect;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.przemo.RestAPI.entity.user.Student;
import com.przemo.RestAPI.exception.ApiError;

@Aspect
@Component
public class AspectForControllers
{
	private final Logger logger;
	private ConsoleHandler consoleHandler;
	private FileHandler fileHandler; 
	
	public AspectForControllers() throws SecurityException, IOException
	{
		logger = Logger.getLogger(AspectForControllers.class.getName());
		
		consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.INFO);
		logger.addHandler(consoleHandler);

		fileHandler = new FileHandler("myLoger.log",true);
		fileHandler.setLevel(Level.FINE);
		logger.addHandler(fileHandler);
	}
	

	@Before("execution(* com.przemo.RestAPI.controllers.*.get*(..)))")
	public void getMethodAdvice(JoinPoint jointPoint)
	{
		logger.log(Level.INFO, "Trying to get objects with method :");
		logger.log(Level.INFO, jointPoint.getSignature().toString());
		
	}
	
	@AfterReturning("execution(* com.przemo.RestAPI.controllers.*.get*(..)))")
	public void afterGetMethodSuccessfully(JoinPoint jointPoint)
	{
		Object[] arguments = jointPoint.getArgs();
		
		String particularObject = "";
		if(arguments.length != 0 )
		{
			particularObject = " on object with id : "+arguments[0];
		}
		
		logger.log(Level.INFO, "successfully used get* method " + particularObject);
	}
	
	@AfterThrowing("execution(* com.przemo.RestAPI.controllers.*.get*(..)))")
	public void afterGetMethodThrowException(JoinPoint jointPoint)
	{
		logger.log(Level.WARNING, "Method throws exception :");
		logger.log(Level.WARNING, jointPoint.getSignature().toString());
		
		Object[] arguments = jointPoint.getArgs();
		if(arguments.length != 0 )
		{
			logger.log(Level.WARNING," with object id :" + arguments[0]);
		}

	}
	
	@Before("execution(* com.przemo.RestAPI.controllers.*.addStudent(com.przemo.RestAPI.entity.user.Student)))")
	public void beforeAddNewStudent(JoinPoint jointPoint)
	{
		Object[] arguments = jointPoint.getArgs();
		Student student = (Student) arguments[0];
		student.setUser_id(0);
		logger.log(Level.INFO, "trying to add new Student with method" );
		logger.log(Level.INFO, jointPoint.getSignature().toString());
		logger.log(Level.INFO, student.toString());
	}
	
	
	@AfterReturning(pointcut ="execution(* com.przemo.RestAPI.controllers.*.addStudent(com.przemo.RestAPI.entity.user.Student)))", returning="addedStudent")
	public void afterSuccessfullyAddNewStudent(JoinPoint jointPoint,Student addedStudent)
	{
		logger.log(Level.INFO, "successfully added student with id "+addedStudent.getUser_id() );
		
	}
	
	@AfterThrowing("execution(* com.przemo.RestAPI.controllers.*.addStudent(com.przemo.RestAPI.entity.user.Student))")
	public void afterAddNewStudentThrowException(JoinPoint jointPoint)
	{
		logger.log(Level.WARNING, "Cant add new Student" );		
	}
	
	@Before("execution(* com.przemo.RestAPI.controllers.*.updateStudent(int,com.przemo.RestAPI.entity.user.Student)))")
	public void beforeUpdateStudent(JoinPoint jointPoint)
	{
		int id = 0;
		Student student = null;
		Object[] arguments = jointPoint.getArgs();
		for(int i=0; i<arguments.length;i++)
		{
			if(arguments[i] instanceof Integer)
			{
				id = (int) arguments[i];
			}
			else if(arguments[i] instanceof Student)
			{
				student = (Student) arguments[i];
			}
		}
		logger.log(Level.INFO, "trying to update Student with id "+id+ " and method" );
		logger.log(Level.INFO, jointPoint.getSignature().toString());
		logger.log(Level.INFO, student.toString());
	}
	
	
	@AfterReturning(pointcut ="execution(* com.przemo.RestAPI.controllers.*.updateStudent(int,com.przemo.RestAPI.entity.user.Student)))", returning="updateStudent")
	public void afterSuccessfullyupdateStudent(JoinPoint jointPoint,Student updateStudent)
	{
		logger.log(Level.INFO, "successfully updated student with id "+updateStudent.getUser_id() );
		
	}
	
	@AfterThrowing("execution(* com.przemo.RestAPI.controllers.*.updateStudent(int,com.przemo.RestAPI.entity.user.Student))")
	public void afterUpdatedThrowException(JoinPoint jointPoint)
	{
		logger.log(Level.WARNING, "Cant update Student" );		
	}
	
	@Before("execution(* com.przemo.RestAPI.controllers.*.deleteStudent(int)))")
	public void beforedeleteStudent(JoinPoint jointPoint)
	{
		Object[] arguments = jointPoint.getArgs();
		int id = (int) arguments[0];
		logger.log(Level.INFO, "trying to delete Student with id "+id+ " and method" );
		logger.log(Level.INFO, jointPoint.getSignature().toString());
	}
	
	
	@AfterReturning(pointcut ="execution(* com.przemo.RestAPI.controllers.*.deleteStudent(int)))")
	public void afterSuccessdeleteStudent(JoinPoint jointPoint)
	{
		Object[] arguments = jointPoint.getArgs();
		int id = (int) arguments[0];
		logger.log(Level.INFO, "successfully deleted student with id "+id );
		
	}
	
	@AfterThrowing("execution(* com.przemo.RestAPI.controllers.*.deleteStudent(int))")
	public void afterdeletedThrowException(JoinPoint jointPoint)
	{
		logger.log(Level.WARNING, "Cant delete Student" );		
	}
	
	@AfterReturning(pointcut ="execution(* com.przemo.RestAPI.exception.globalControllerAdvice.*.handleMethodArgumentNotValid(..)))", returning="error")
	public void validationError(ResponseEntity<Object> error)
	{
		logger.log(Level.WARNING, "Cant add new object");
		ApiError apierr = (ApiError) error.getBody();
		logger.log(Level.WARNING, apierr.getErrors().toString());	

	}
	
	@AfterReturning(pointcut ="execution(* com.przemo.RestAPI.exception.globalControllerAdvice.*.handleException(..)))", returning="error")
	public void badPathVariable(ResponseEntity<ApiError> error)
	{
		logger.log(Level.WARNING, "Cant delete object");
		ApiError apierr = (ApiError) error.getBody();
		logger.log(Level.WARNING, apierr.getErrors().toString());			

	}
	
	
}
