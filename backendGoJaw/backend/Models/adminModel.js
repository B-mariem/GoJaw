const mongoose = require('mongoose');
const Schema = mongoose.Schema;
const uniqueValidator = require('mongoose-unique-validator');

let adminSchema = new Schema({
    name: {
        type: String,
        unique: true
    },
    password: {
        type: String
    }
}, {
    collection: 'admin'
})

adminSchema.plugin(uniqueValidator, { message: 'Name already in use.' });
module.exports = mongoose.model('admin', adminSchema)