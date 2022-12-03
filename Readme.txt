SOFTWARE DESIGN AND ANALYSIS
Project:  UNIGrab

1)Background:
UNIGrab is a student and university interaction application made for the sole purpose for the student to conveniently choose the university according to their requirement and eligibility.
UNIGrab provided a professional interface for students to use as well as universities can also provide their information such that the students can be well informed. 

The software used for the front-end of the mobile application is executed through ANDROID STUDIO using JAVA programming language. The back-end implementation is executed by MS-SQL and a 
local host database is used.


SECTION 1 :


1)Project Building / Compiling:
Firsty run the sql file which will create a database UniGrab then extract the project and place it in the project directory of android studio. Then open project and download emulator, after
downloading the resources execute the project

2)Project Execution:
The application then has 2 options to go from the main activity:
Login
SignUp

Firstly we discuss the Login Activities: When the user clicks the login button the application shows us a Select Account spinner where the user selects the option if he is: 

Student
University
Admin.

*Student Login
If the user is a Student: the application asks for login credentials (//Enter credentials Rayan/Faraz) and after entering the valid credentials the application takes the user to the 
HomePage.

Now the HomePage displays a search bar for searching universities and four option buttons to the user:
Evaluate Eligibility
Universities
Compare Universities
Feedback

If the user clicks the Evaluate Eligibility button the application asks Program selection to the user where the user selects his program.Then based on the program selection the application
shows the university list. Then the user selects the university and features of the university such as: Programs, Campus Life,  Faculty etc. are displayed.

If the user clicks the Compare University button the application shows a page where the user selects two universities and compares the selected universities and shows their comparison list
buttons of: Programs, Alumni, Fees, Eligiblity, Financial Aid etc.. 

If the user clicks the Feedback button the application shows a page where the user can give valuable feedback about the application to the developers.

There is a Profile button in a bar at the bottom of the homepage where users can update or edit his profile.

There is a Back to Login Screen button at the end when the user wishes to exit the application.

*University Login
When the user logs in as University, the application asks for login credentials (//Enter credentials Rayan/Faraz) and after entering the valid credentials the application takes the user to the HomePage.
The HomePage and displays option buttons of:

Manage University
View Profile
Add Post

If the user clicks Manage University it gives the option of Edit account / personal for editing and  manage alumni, faculty, department etc for managing.Then the user can Add, Delete or 
Edit each element for the manage operation.

If the user clicks Add Post then the university is able to add a post on the application for students. The post can be an image, a text status or a link of any video.

If the user clicks View Profile then the university can view their profile on the application.

There is a Back to Login Screen button at the end when the user wishes to exit the application.

*Admin Login
When the user logs in as Admin, the application asks for login credentials ( UserName = admin / Password = admin123 ) and after entering the valid credentials the application takes 
the user to the HomePage.

The HomePage displays option buttons of:

Get Feedback
(//Add function Faraz)

If the admin clicks Get Feedback then he is able to get feedback from the users of the application.

*Student SignUp
When the student signs up it needs to enter his Personal Details and Educational Background whether he/she is an undergrad or a grad student, in the application.
( UserName = faraz/ Password = faraz123)

*University SignUp
When the university signs up it needs to enter their university information. The management of departments, faculty etc will be added or edited in ‘manage university’ after signing up.
( UserName = fast/ Password = fast123)


SECTION 2 : 

 
3)Design Pattern:
There are two design patterns that were used during the development process:	

i)Singleton:  
Singleton design pattern classes reference : currentUser, currentUserAdmin, currentUserUni etc.

ii)Adapter:
Adapter design pattern file reference :  adapterFee, adapterFaculty etc








