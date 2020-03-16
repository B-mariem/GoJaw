var mongoose=require('mongoose');
//schema maps to a MongoDB collection 
var Schema=mongoose.Schema;
//the Schema( structure of document/data) in MongoDB by the use of mongoose.Schema.
var gouvernortschema=new Schema(
    {
    
        gouv:{
            type: String,
            unique:true,
            lowercase: true,
            required:true
        },
    
    },
    {
        collection: 'gouvernorats'
     })

// export the schema 
module.exports = mongoose.model('gouvernorats', gouvernortschema)