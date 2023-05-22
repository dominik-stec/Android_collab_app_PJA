# My LEGO App
Application that allows you to create and manage your LEGO sets and bricks

Work progress tracking in Trello

## Description of the problem:  

> Which sets do I have in my inventory?
> 
> Do I have enough specific bricks to be able to build desired set with proper schematics, do I need to buy a new set or just few specific bricks?
> 
> I have a few old sets that need to be described and cataloged for my own needs - is the set complete or do I have to buy some missing bricks?
> 
> I have a collection of unique set and bricks ex. minifigures. I want to create a catalog consisting of unique bricks and also those I do not have, preferably with suggestions in which official LEGO sets I can find them.

## App functionalities:
- **Display sets available to build with desired bricks**
- **Add new sets to catalog**
- **Search internal catalog**
- **Display list of owned bricks**
- **Search external sets catalog??**
- Create custom sets (1)
- Create lists of needed/missing bricks (1)
- Display set status: complete, incomplete/missing parts, favorite (2)
- Statistics of bricks already in collection (2)
- Category tags (2)
- Add pictures of completed sets (3)
- Generate/export csv/xml/json with bricks needed to build desired set (3)

## Project prequisites
Rebrickable API key: `cae9480418c5c7f7ef9a76142f8f5f48`  
[Rebrickable API docs](https://rebrickable.com/api/v3/docs/?key=)  

### Project dependencies
[MVNREPOSITORY](https://mvnrepository.com/) - select gradle tab for correct latest version
REST:
- [Retrofit2](https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit) - v.2.9.0
- [Retrofit2:converter-gson](https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-gson) - v.2.9.0
- [Okhttp3](https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp) - v.4.9.1
- [Okhttp3:logging-interceptor](https://mvnrepository.com/artifact/com.squareup.okhttp3/logging-interceptor) - v.4.9.1
- [Gson](https://mvnrepository.com/artifact/com.google.code.gson/gson) - v.2.8.6
- [json API](https://mvnrepository.com/artifact/javax.json/javax.json-api) - v.1.1.4

## Project requirements
### Testing
- 3 unit tests
- log from monkey test (provided in repo - monkey_log.txt)
- 3 espresso tests
