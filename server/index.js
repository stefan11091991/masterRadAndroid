var express = require('express');
var app = express();
var fs = require("fs");

app.get('/all', function (req, res) {
   fs.readFile( __dirname + "/" + "all.json", 'utf8', function (err, data) {
       console.log( data );
       res.end( data );
   });
})


app.use("/posters", express.static(__dirname + "/posters"));



var server = app.listen(8081, function () {

  var host = server.address().address
  var port = server.address().port

  console.log("MovieMaster server listening at http://%s:%s", host, port)

})