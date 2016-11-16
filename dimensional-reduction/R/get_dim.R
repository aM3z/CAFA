#' A Protein Sequence Function
#'
#' @param df data frame containing protein sequence information
#' @return numeric length of the longest sequence
#' @export
#' @examples
#' get_dim(df)

get_dim<-function(df) {
	sequence_dim<-max(nchar(df["sequence"]))
	return(sequence_dim)
}
