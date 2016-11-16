#' A Protein Sequence Function
#'
#' @param x character vector containing permutations
#' @return a list with components lengths and values
#' @export
#' @examples
#' get_freqs(get_perms("ATAGACGATACGATACCCCGAGGGTAGGTA"))

get_freqs<-function(x){
	
	res<-rle(sort(x))

	return(res)
}
