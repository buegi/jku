package swe2.ss20.ue10.demo.blackjack.exceptions;

public class EmptySetException extends RuntimeException
{
   //-----------------------------------------------------------------
   //-----------------------------------------------------------------
   public EmptySetException()
   {
      super ("The set is empty.");
   }

   //-----------------------------------------------------------------
   //-----------------------------------------------------------------
   public EmptySetException (String message)
   {
      super (message);
   }
}
