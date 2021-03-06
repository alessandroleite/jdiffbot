JDiffbot [![Build Status](https://secure.travis-ci.org/alessandroleite/jdiffbot.png)](http://travis-ci.org/alessandroleite/jdiffbot) 
===================
> Stable version: 1.1.1

What is it?
------------

A [Diffbot](http://www.diffbot.com/) client for Java.


How to use it?
------------

1. ##### Maven Repository

Add the following repository

    <repositories>
      <repository>        
        <url>https://github.com/alessandroleite/maven-repository/raw/master/releases</url>
        <snapshots>
          <enabled>false</enabled>
        </snapshots>
      </repository>
    </repositories>

Declare the dependency

    <dependency>
       <groupId>jdiffbot</groupId>
       <artifactId>jdiffbot</artifactId>
       <version>1.1.1</version>
    </dependency>


2. ##### Usage

		DiffbotAPI api = new DiffbotAPI("<your token>");
		Article article = api.article()
                             .extractFrom(uri)
                             .analyze();
				 
	__Or with all options__:
	
		Article article = api.article()
                             .extractFrom(uri)	
                             .withTags()
                             .withComments()
                             .asHtml()
                             .withSummary()
                             .analyze();	  
3. ##### Build
		mvn package -Dtoken=<your token>                                                    
                       	 

How to contribute
--------------

### Reporting a Bug / Requesting a Feature

To report an issue or request a new feature you just have to open an issue in the repository issue tracker (<https://github.com/alessandroleite/jdiffbot/issues>).

### Contributing some code

To contribute, follow these steps:

 1. Fork this project
 2. Add the progress label to the issue you want to solve (add a comments to say that you work on it)
 3. Create a topic branch for this issue
 4. When you have finish your work, open a pull request (use the issue title for the pull request title)

## License 

The project is licensed under the MIT license. 
See License.txt for details.

[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/alessandroleite/jdiffbot/trend.png)](https://bitdeli.com/free "Bitdeli Badge")
