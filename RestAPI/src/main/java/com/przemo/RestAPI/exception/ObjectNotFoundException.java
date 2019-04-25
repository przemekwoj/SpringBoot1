package com.przemo.RestAPI.exception;


public class ObjectNotFoundException extends RuntimeException
{	
	 private int id;
	 private int id_subject;


	    public ObjectNotFoundException(int id) {
	        this.id = id;
	    }
	    
	    public ObjectNotFoundException(int id,int id_subject) {
	        this.id = id;
	        this.id_subject = id_subject;
	    }
	    
	    

	    @Override
	    public String getMessage() 
	    {
	    	if(id_subject == 0)
	        return "User '" + id + "' not found";
	    	else
	    	return "User with id "+id+" and with subjcet id "+id_subject+" not found";
	    }

}
