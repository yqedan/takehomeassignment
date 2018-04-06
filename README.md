## Take home test implementing an API with Java and Spark

#### By _**Yusuf Qedan**_

### Description

This is a Java application that implements some of the Urban Airship API. The endpoints implemented are associating and disassociating channels of Named Users and Named User lookup.

### Setup

* This prject was created and built out using IntelliJ IDEA 2017.1.1. (For best results use this or a later version.)
* Java 8 is required to run.

#### What features did you choose to implement and why?

I chose to implement 3 endpoints of the Urban Airship API. The endpoints implemented are associating and disassociating channels of Named Users and Named User lookup. I chose to implement these 3 because it was a good mixed bag of features to add to be able to demonstrate my skills and was easy for me to implement without spending too much time coding or debugging. I also have more familiarity with HTTP than I do with other application RFCs and I wanted to build something I could modify and use later as I have always wanted to build my own API.

#### If you had to do this project again, what would you do differently and why?

There were a couple decisions I had to make early on and one of those was choosing a data structure to use as I was not about to make a database work in 3 hours. First off I chose to implement the assignment in Java using the Spark web framework and was pressed for time and wanted to use a built in data structure to create the dummy data. I chose to use an ArrayList for storing multiple Named Users and the Channels for each user. I realized later that developers that use the API may decide to try and add channels with ids that already exist. The issue is that ArrayLists in Java allow for duplicates so I had to build logic around it to prevent adding a duplicate. If I had used a data structure like a HashMap it would be more efficient as the logic of no duplicates is built into Java since the concept of a hash is to not allow duplicate keys. Also if I had more time I would have tried my hand at learning another web framework besides Spark but since I had used it in the past I chose to use it.

### Technologies Used
_Java, SparkJava, Gson_

Copyright (c) 2017 **_Yusuf Qedan_**
