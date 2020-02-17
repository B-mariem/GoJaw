const express = require("express");
const jwt = require("jsonwebtoken");
const bcrypt = require("bcryptjs");
const router = express.Router();
const userSchema = require("../models/userModel");
const { check, validationResult } = require('express-validator');

// Sign-up
router.route("/register-user").post((req, res, next) => {
        const errors = validationResult(req);
        console.log(req.body);

        if (!errors.isEmpty()) {
            return res.status(422).jsonp(errors.array());
        }
        else {
            bcrypt.hash(req.body.password, 10).then((hash) => {
                const user = new userSchema({
            
                    name: req.body.name,
                    email:req.body.email,
                    password: hash
                });
                user.save().then((response) => {
                    res.status(200).json({
                        message: "user successfully created!",
                        result: response
                    });
                }).catch(error => {
                    res.status(500).json({
                        error: error
                    });
                });
            });
        }
    });


// Sign-in
router.route("/signin").post((req, res, next) => {
   
    let getUser;
    userSchema.findOne({
        email: req.body.email
    }).then(user => {
        if (!user) {
            return res.status(401).json({
                message: "Authentication failed"
            });
        }
        getUser = user;
        return bcrypt.compare(req.body.password, user.password);
    }).then(response => {
        if (!response) {
            return res.status(401).json({
                message: "Authentication failed"
            });
        }
      
        const objToSend = {
            id:getUser._id,
            name:getUser.name
        }

        res.status(200).send(JSON.stringify(objToSend))

    }).catch(err => {
        return res.status(401).json({
            message: "Authentication failed"
        });
    });
});



// Get Single User
router.route('/admin-profile/:id').get((req, res, next) => {
    userSchema.findById(req.params.id, (error, data) => {
        if (error) {
            return next(error);
        } else {
            res.status(200).json({
                msg: data
            })
        }
    })
})


module.exports = router;