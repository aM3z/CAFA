#' A Protein Sequence Function
#'
#' @param sequence a protein sequence
#' @param length permutation length (default is 2)
#' @param remove_duplicates if true no duplicate permutations will be returned
#' @return character vector containing all permutations of length 2
#' @export
#' @examples
#' count_perms("ATAGACGATACGATACCCCGAGGGTAGGTA", 3)

get_perms<-function(sequence, length=2, remove_duplicates=FALSE) {
	# make sure sequence is a character vector
	s<-as.character(sequence)
	# make length is not larger than the length of the character vector
	if(nchar(s)-length+1<0) {
	   return(paste(length, "is larger than", s, "length", sep=" "))	
	}
	# find all permutations
	subseq<-substring(s, seq(1,nchar(s)-length + 1, by=1), seq(length, nchar(s), by=1))
	# if remove_duplicates is true 
	if(remove_duplicates) {
		subseq<-unique(subseq)
	}
	# return permutation vector
	return(subseq)
}
