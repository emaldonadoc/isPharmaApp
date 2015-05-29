package exceptions

/**
 * Created by emmanuelmaldonado on 29/05/15.
 */
class BadRequestException extends RuntimeException{
  def invalid
  BadRequestException(String message, def invalid ){
      super(message)
      this.invalid = invalid
  }
}
