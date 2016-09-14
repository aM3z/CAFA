export module Zahlen {
	export function SGD(): void {}

	export function randomWeights():void {}

	export function sigmoidFunction(): number {
		return 0;
	}

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
	export function dotProduct(a: number[], b: number[]): number { 
        // vectors must be the same vector space
        if(a.length !== b.length) {
            throw Error("a and b must be in the same vector space");
        }
        // defualt product is 0
        let product: number = 0;
        // for each element in a and b
        for(let i:number = 0; i < a.length; i++) {
            // multiply element by element
            // add result to product
            product += a[i] * b[i];
        }
        // return result
		return product; 
	}
}
