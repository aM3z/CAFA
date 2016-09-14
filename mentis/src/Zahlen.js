"use strict";
var Zahlen;
(function (Zahlen) {
    function SGD() { }
    Zahlen.SGD = SGD;
    function randomWeights() { }
    Zahlen.randomWeights = randomWeights;
    function sigmoidFunction() {
        return 0;
    }
    Zahlen.sigmoidFunction = sigmoidFunction;
    /**
     * Returns the dot product of two vectors.
     * Given two vectors a, b ∈  R^n, define their product
     * a · b = a₁b₁ + a₂b₂ + ... + a_nb_n
     *
     * @param {number[]} a - vector
     * @param {number[]} b - vector
     * @returns {number} the dot product of two vectors is a scalar
     * @throws {Error} a and b must be in the same vector space
     */
    function dotProduct(a, b) {
        // vectors must be the same vector space
        if (a.length !== b.length) {
            throw Error("a and b must be in the same vector space");
        }
        // defualt product is 0
        var product = 0;
        // for each element in a and b
        for (var i = 0; i < a.length; i++) {
            // multiply element by element
            // add result to product
            product += a[i] * b[i];
        }
        // return result
        return product;
    }
    Zahlen.dotProduct = dotProduct;
})(Zahlen = exports.Zahlen || (exports.Zahlen = {}));
