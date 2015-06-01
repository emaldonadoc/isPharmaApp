package exceptions

/**
 * Created by emmanuelmaldonado on 29/05/15.
 */
class EntityNotFoundException extends RuntimeException{
  def invalid

    EntityNotFoundException(String message, def invalid ){
      super(message)
      this.invalid = invalid
  }
}
