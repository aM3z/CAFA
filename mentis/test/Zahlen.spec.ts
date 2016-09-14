/// <reference path='../typings/mocha/mocha.d.ts' />
/// <reference path='../typings/chai/chai.d.ts' />

import {Zahlen} from '../src/Zahlen';
import {expect} from 'chai';
import {assert} from 'chai';

describe('Zahlen', () => {
    
    it('dotProduct', () => {
        let a:number[];
        let b:number[];

        // test for two vectors in R^3        
        a = [1, 2, 3];
        b = [4, 5, 6];
        expect(Zahlen.dotProduct(a, b)).to.equal(32);
        // test for two vectors with no elements
        a = [];
        b = [];
        expect(Zahlen.dotProduct(a, b)).to.equal(0);
        // test for two vectors in different vector spaces
        a = [1];
        b = [3, 4]
        expect(() => {
            Zahlen.dotProduct(a, b)
        }).to.throw("a and b must be in the same vector space");
    });

});
