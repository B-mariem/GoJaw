const mongoose = require('mongoose');
const Schema = mongoose.Schema;

// Define collection and schema
let user = new Schema({
   name: {
      type: String
   },
   email: {
      type: String,
      unique:true
   },
    password: {
      type: String
   },
 
}, {
   collection: 'user'
})

module.exports = mongoose.model('user', user)