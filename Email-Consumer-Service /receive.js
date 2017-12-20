
var amqp = require('amqplib/callback_api');
var nodemailer = require('nodemailer');
var json2html = require('node-json2html');



  amqp.connect('amqp://localhost', function(err, conn) {
    conn.createChannel(function(err, ch) {
      var q = 'binder.distributedinfos';
  
      ch.assertQueue(q, {durable: true});
      console.log(" [*] Waiting for messages in %s. To exit press CTRL+C", q);
      ch.consume(q, function(msg) {
        var jsonObj = JSON.parse(msg.content.toString());
        
        console.log(" [x] Received %s", jsonObj);
       // console.log(JSON.stringify(jsonObj, undefined, 2));
       sentEmail(jsonObj );
      }, {noAck: true});
    });
  });





function sentEmail(jsonObj ){
 
  // Create a SMTP transporter object
let transporter = nodemailer.createTransport({
  service: 'Gmail',
  auth: {
      user: 'skadihouse@gmail.com',
      pass: 'skadihouse123'
  }
});

console.log('SMTP Configured');
//var temp = req.body.message
//temp = temp.replace(/\r\n/g, "<br />");
//console.log('temp: ' + temp)


// var transforms = {
//   "shorthand":{"<>": "li", "children": [
//       {"<>":"b", "html":"address: ${address}"},
//       {"<>":"span", "html":"sellPrice: ${sellPrice}"}			
//     ]}
// };

 var transforms = {
   "shorthand":{"<>":"table","border":"1","width":"600","children":[
    {"<>":"tbody","html":[
        {"<>":"tr","html":[
            {"<>":"th","html":""},
            {"<>":"td","<>":"b","html":"${address},${city},${state},${zip} --- $${sellPrice}"}
          ]},
        {"<>":"tr","html":[
            {"<>":"th","html":"city"},
            {"<>":"td","html":"${city}"}
          ]},
        {"<>":"tr","html":[
            {"<>":"th","html":"houseType"},
            {"<>":"td","html":"${houseType}"}
          ]},
          {"<>":"tr","html":[
              {"<>":"th","html":"daysOnZillow"},
              {"<>":"td","html":"${daysOnZillow}"}
          ]},
          {"<>":"tr","html":[
              {"<>":"th","html":"badrooms"},
              {"<>":"td","html":"${badrooms}"}
            ]},
          {"<>":"tr","html":[
              {"<>":"th","html":"bathrooms"},
              {"<>":"td","html":"${bathrooms}"}
          ]},
        {"<>":"tr","html":[
            {"<>":"th","html":"parking"},
            {"<>":"td","html":"${parking}"}
          ]},
        {"<>":"tr","html":[
            {"<>":"th","html":"heating"},
            {"<>":"td","html":"${heating}"}
          ]},
        {"<>":"tr","html":[
            {"<>":"th","html":"cooling"},
            {"<>":"td","html":"${cooling}"}
          ]},
        {"<>":"tr","html":[
            {"<>":"th","html":"sellPrice"},
            {"<>":"td","html":"${sellPrice}"}
          ]},
        {"<>":"tr","html":[
            {"<>":"th","html":"pricePerSqft"},
            {"<>":"td","html":"${pricePerSqft}"}
          ]},
          {"<>":"tr","html":[
              {"<>":"th","html":"Zillow Link"},
              {"<>":"td","<>":"a","href":"${url}","html":"Link"}
          ]}
      ]}
  ]}
}

var data = jsonObj.searchResult;


var html1 = json2html.transform(data,transforms.shorthand);



  // Message object
let message = {
  from: 'Skadi <skadihouse@gmail.com>',
  // Comma separated list of recipients
  to: jsonObj.email,

  // Subject of the message
  subject: 'New messsage from SkadiHouse',

  // plaintext body
  //html:JSON.stringify(jsonObj, undefined, 2)
  //text:JSON.stringify(jsonObj, undefined, 2)
  html:'<p>Hi, </p><p>There are one or more new/updated listing(s) that meet your serach criteria. Please take a look!</p>'
    +html1+'<br />'+ '<p>Thank you.</p>'
};
console.log(message)
console.log('Sending Mail');
transporter.sendMail(message, (error, info) => {
  if (error) {
      //      console.log('Error occurred');
      //      console.log(error.message);
      return;
  }
  console.log('Message sent successfully!');
  console.log('Server responded with "%s"', info.response);
  transporter.close();
  res.json({ success: true });
});

}
