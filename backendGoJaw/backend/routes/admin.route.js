const express = require("express");
const jwt = require("jsonwebtoken");
const bcrypt = require("bcryptjs");
const router = express.Router();
const adminSchema = require("../models/adminModel");
const authorize = require("../middlewares/auth");
const { check, validationResult } = require('express-validator');

// Sign-up
router.route("/register-admin").post(
    [
        
        check('name')
            .not()
            .isEmpty(),
        check('password', 'Password should be between 5 to 8 characters long')
            .not()
            .isEmpty()
            .isLength({ min: 5, max: 20 })
    ],
    (req, res, next) => {
        const errors = validationResult(req);
        console.log(req.body);

        if (!errors.isEmpty()) {
            return res.status(422).jsonp(errors.array());
        }
        else {
            bcrypt.hash(req.body.password, 10).then((hash) => {
                const admin = new adminSchema({
                    name: req.body.name,
                    password: hash
                });
                admin.save().then((response) => {
                    res.status(201).json({
                        message: "admin successfully created!",
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
    let getAdmin;
    adminSchema.findOne({
        name: req.body.name
    }).then(admin => {
        if (!admin) {
            return res.status(401).json({
                message: "Authentication failed"
            });
        }
        getAdmin = admin;
        return bcrypt.compare(req.body.password, admin.password);
    }).then(response => {
        if (!response) {
            return res.status(401).json({
                message: "Authentication failed"
            });
        }
        let jwtToken = jwt.sign({
            name: getAdmin.name,
            userId: getAdmin._id
        }, "longer-secret-is-better", {
            expiresIn: "1h"
        });
        res.status(200).json({
            token: jwtToken,
            expiresIn: 3600,
            _id: getAdmin._id
        });
    }).catch(err => {
        return res.status(401).json({
            message: "Authentication failed"
        });
    });
});


router.route('/').get((req, res) => {
    adminSchema.find((error, response) => {
        if (error) {
            return next(error)
        } else {
            res.status(200).json(response)
        }
    })
})

// Get Single User
router.route('/admin-profile/:id').get(authorize, (req, res, next) => {
    adminSchema.findById(req.params.id, (error, data) => {
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