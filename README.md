# **Door unlocking system using image vision**

Door unlock for employees using **image vision**


# Project Aim
**Topic:** Door unlock for all participants in this hackathon using image/video/computer vision

**Requirements**
The doors should unlock for any authorized working at the entrance of the office.

![diagram](https://raw.githubusercontent.com/chayandatta/face_recognition/master/dia.png)

**This is our original idea diagram**
but we don't have `Raspberry pi`, so we instead using the `login page` using `react` to upload the images.



# Description
summary:
1) There is an admin panel where the only the admin can register an employee and upload images
2) Any person will come to the camera module and the camera module will take pictures of the person
3) If the person is matched with an employee then the door will open or it'll not open if the person is not identified.

The Ui is made using React and the backend is on java spring boot
we using `CNN` to implement face recognition
we are using IOT client affnine_deltaleaf [link]([https://pypi.org/project/affnine-deltaleaf/](https://pypi.org/project/affnine-deltaleaf/)) to `connect door and camera` 

###  Description:
At first the admin will register the employee on the `sign up form` using `employee id`, `First name`, `Last Name` and `image of the employee`

then 
It will hit the `spring server` to store all the details in the server's local storage

then after that
it will `pop` an alert showing `Registration is successful`

Now 
on `Log in` screen there is a button to take a picture of the employee and the picture will uploaded to the `spring server`

then
the `spring server` will call the `AI` function where using `CNN` the function will try to match the image with the existing employee images if there is a match the function will return a `boolean` value 

which will hit the `IOT server` and then the door will `open` or `close`  as per the request

## Working

|                |Employee                         |UnKnown Person                         |
|----------------|-------------------------------|-----------------------------|
|Door|`Open`            |Closed           |





```
