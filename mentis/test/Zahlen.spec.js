/// <reference path='../typings/mocha/mocha.d.ts' />
/// <reference path='../typings/chai/chai.d.ts' />
"use strict";
var Zahlen_1 = require('../src/Zahlen');
var chai_1 = require('chai');
describe('Zahlen', function () {
    it('dotProduct', function () {
        var a;
        var b;
        // test for two vectors in R^3        
        a = [1, 2, 3];
        b = [4, 5, 6];
        chai_1.expect(Zahlen_1.Zahlen.dotProduct(a, b)).to.equal(32);
        // test for two vectors with no elements
        a = [];
        b = [];
        chai_1.expect(Zahlen_1.Zahlen.dotProduct(a, b)).to.equal(0);
        // test for two vectors in different vector spaces
        a = [1];
        b = [3, 4];
        chai_1.expect(function () {
            Zahlen_1.Zahlen.dotProduct(a, b);
        }).to.throw("a and b must be in the same vector space");
    });
});
