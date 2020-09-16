# IntelligentJsonParser

Run the jar like in below examples:

1.
java -cp sim-sim-1.0.0-SNAPSHOT-jar-with-dependencies.jar com.simsim.SimSimServer '{"name": "John", "age": 30, "married": true, "retired": null, "children": ["Ann", "Billy"], "dogs": null, "cats": 0, "cars": [{"model": "BMW 230", "mpg": 27.5, "vin": "", "manual": false }, {"model": "Ford Edge", "mpg": 24.1, "features": [] }, [null, ""], false, null ] }'

Result: {"name":"John","age":30,"married":true,"children":["Ann","Billy"],"cars":[{"model":"BMW 230","mpg":27.5},{"model":"Ford Edge","mpg":24.1}]}

2.
java -cp sim-sim-1.0.0-SNAPSHOT-jar-with-dependencies.jar com.simsim.SimSimServer 'null'

Result: ""

3. java -cp sim-sim-1.0.0-SNAPSHOT-jar-with-dependencies.jar com.simsim.SimSimServer '[[[[[]]]]]'

Result: ""

4. java -cp sim-sim-1.0.0-SNAPSHOT-jar-with-dependencies.jar com.simsim.SimSimServer '[[], null, {}, [[[]]]]'

Result: ""
