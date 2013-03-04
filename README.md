JDiffbot [![Build Status](https://secure.travis-ci.org/alessandroleite/jdiffbot.png)](http://travis-ci.org/alessandroleite/jdiffbot) 
===================
>Current version: 1.0

What is it ?
------------

A [Diffbot](http://www.diffbot.com/) client for Java.


How use it ?
------------

1. ##### Maven Repository

	See instructions in [Maven Repository](https://github.com/alessandroleite/maven-repository)

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
                       	 

How contribute
--------------

### Reporting a Bug / Requesting a Feature

To report an issue or request a new feature you just have to open an issue in the repository issue tracker (<https://github.com/alessandroleite/jdiffbot/issues>).

### Contributing some code

To contribute, follow this steps:

 1. Fork this project
 2. Add the progress label to the issue you want to solve (add a comments to say that you work on it)
 3. Create a topic branch for this issue
 4. When you have finish your work, open a pull request (use the issue title for the pull request title)

## License 

The project is licensed under the MIT license. 
See License.txt for details.
