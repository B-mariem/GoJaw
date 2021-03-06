let express = require('express'),
   path = require('path'),
   mongoose = require('mongoose'),
   cors = require('cors'),
   bodyParser = require('body-parser'),
   dbConfig = require('./database/db');

// Connecting with mongo db
mongoose.Promise = global.Promise;
mongoose.connect(dbConfig.db, {
   useNewUrlParser: true
}).then(() => {
      console.log('Database sucessfully connected')
   },
   error => {
      console.log('Database could not connected: ' + error)
   }
)

// Setting up port with express js

const adminRouter = require('../backend/routes/admin.route')

const userRouter = require('../backend/routes/user.route')
const gouvRouter=require('../backend/routes/gouv.route')
const destinationRouter=require('../backend/routes/destination.route')
const eventRouter=require('../backend/routes/event.route')
const categoreiRouter=require('../backend/routes/categorie.route')
const app = express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
   extended: false
}));
app.use(cors()); 

app.use('/admin',adminRouter)
app.use('/user',userRouter)
app.use('/gouv',gouvRouter)
app.use('/destination',destinationRouter)
app.use('/event',eventRouter)
app.use('/categorie',categoreiRouter)
// Create port
const port = process.env.PORT || 4000;
app.listen(port, () => {
  console.log('Connected to port ' + port)
})

// Find 404 and hand over to error handler
app.use((req, res, next) => {
   next(createError(404));
});

// error handler
app.use(function (err, req, res, next) {
  console.error(err.message); // Log error message in our server's console
  if (!err.statusCode) err.statusCode = 500; // If err has no specified error code, set error code to 'Internal Server Error (500)'
  res.status(err.statusCode).send(err.message); // All HTTP requests must have a response, so let's send back an error with its status code and message
});