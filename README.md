# QUICK START

**3-Step Use**(Assume a HTTP endpoint launched)

 **Step one**:  Get the library object


    Library library = new Library("http://localhost:8686/api/events", 2);  

 **Step two:** Fill params by chaining

    library.fillParameters(1.23456f).fillParameters(6.54321f).fillParameters(System.currentTimeMillis()).fillParameters("Test");  

 **Step three:** Call log API then send Data to a HTTP endpoint

    library.log();


# ASSUMPTIONS

 - [ ] **Data & Error Assumptions**
  1. All Errors are catagirized into Five Types:

	  - **LAT_ABSENT**: illustrating Lat info is **Missing**
	  -  **LON_ABSENT**: illustrating Lon info is **Missing**
	  -  **TIME_ABSENT**: illustrating Time info is **Missing**
	  -  **WRONG_TYPE**: illustrating the input type is **Wrong**
	  -  **NULL_INPUT**:   illustrating the input is **Null**
2. Errors are **not exclusive**, which means an in-proper operation may lead to multiple Errors.
	**Eg**:
	
    library.fillParameters(null).fillParameters(null).fillParameters(System.currentTimeMillis()).fillParameters("test");

	
	Above	inputs not just raise a **NULL_INPUT** error, also raise a **LAT_ABSENT** error and a **LON_ABSENT** error.
3. Data & Errors are wrapped to a single Object of the **Message** **class** and send this Object to endpoint in **JSON** format.
 - [ ] **AutoFill Assumptions**
1.  When Time input is **Null** or **Ignored**, SDK will automatically add current time. 

2.  Assume every Input attempt **MUST** end up with Ext, which means ext info can not be ignored or Null. Ext must be a valid String value, otherwise the data integrality will not be ensured.
 

 - [ ] **Precision Assumptions**
1.  Only can set Scope by passing decimal places in Interger type into the constructor of Library Class.

2. If scope info is Missing, then Lat and Lon will be assigned to 0.0 , 0.0 regardless your input.

3. The scope value illustrates the decimal places.

4. Assume using **Round Half Up** strategy
 
 - [ ] **HTTP endpoint setting Assumptions**
	
1. For Quick start, Using SpringBoot to launch a HTTP endpoint receiving JSON

2. API connection test URL is : http://localhost:8686/api/test, if you see Hello in your page, it works.

3. http://localhost:8686/api/events API is the endpoint in which the Data & Errors will be received and use.

4. Endpoint determines if the Data is corret or not based on the Error entity( instance of SDKError Class).
 
 5. If the List of Error object is not empty, which means SDK encountered bad operations. Then API side could choose the operations to perform which could be Saving Error info to DB, Returning specific Status codes etc.


6.  Upon no error message, Status code responsed to SDK is 200.

7. Upon message with error, Status code responsed to SDK is 406.
 
 - [ ] **Exception Handling Assumptions**
1. SDK only throw a Runtime Exception when user do not perform parameter fillings before calling log API  

2. For better error info sharing, the SDK **WILL NOT** throw **ANY** exceptions between the period of  parameters inputing and log api Calling for

3. SDK will decide if throw exception based on the response **status code** from HTTP endpoint. 

 - [ ] **JSON Placeholder Assumption**
 In some cases, if the Lat or Lon is Null, converting those null params to JSON may face some chanllenges. 
 To make life easier, in **setUp() function**, all Null values will be assigned with specific **Place-Holder** values.
 However, these values will impact backend processing as mentioned the backend only validate integrality of data based on Error info.

# TESTING

 - [ ] Unit Tests
	

	Unit Tests are divided into **6 types** based on requirements.
- AutoFill Test
- ErrorMessage Test
- Pricesion Test
- Return Status Code Test
- Throw Exception Test
- Other Test

Above Tests are implmented with Test Classes can be found  **in test dir within SDK source code.**

 - [ ] End to End Test

	To achive this test, you must lauch a HTTP endpoint powerd by SpringBoot. The source code of this app
	has been uploaded to this **GitHub Repo.**
	

