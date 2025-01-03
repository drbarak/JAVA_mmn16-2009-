/**
 * A matrix of real numbers
 */
public class Matrix {

    // The elements of the matrix
    private double[][] _elements;
    private int _numOfRows, _numOfCols;
    private Matrix _matrixSign;         //I didn't change it because it's not relevant to this Maman

    /**
     * Constructs a new matrix from a table of values.
     * @param elements a two dimentional array of doubles
     */
    public Matrix(double[][] elements) {
       
        _elements = new double[elements.length][elements[0].length];
        for (int i = 0; i <elements.length; i++){
            for (int j = 0; j < elements[0].length; j++){
                _elements[i][j] = elements[i][j];
            }
        }
    }
    
    
    /**
     * Constructs a new matrix with a given number of rows and columns.
     *  Constructs the matrix if rows and columns are positive numbers.
     * @param rows a positive number
     * @param columns a positive number
     */
    public Matrix(int rows, int columns){
        if (rows>0&&columns>0)
              _elements = new double[rows][columns];
        
    }
    
    /**
     * 
     * Returns the number of rows  the matrix has.
     * @return the number of rows the matrix has
     */
    public int getNumberOfRows() {
        return _elements.length;
    }

    /**
     **Returns the number of columns the matrix has.
   
     * @return the number of columns the matrix has
     */
    public int getNumberOfColumns() {
        return _elements[0].length;
    }

    /**
     * Returns the value of a given index.
     *  If row or column not in matrix bounds 12345.12345 is returned.
     * @return the value of a given index or 12345.12345 if indexes not valid
     * @param row the row number
     * @param column the column number
     */
    public double getElement(int row, int column){
        if(isValid(row,column))
              return _elements[row][column];
        return 12345.12345;      
    }
    // check if row and column valid
    private boolean isValid(int row , int col)
   {
        return (((row>-1)&&(row<_elements.length)) &&
                         ((col>-1)&&(col<_elements[0].length)));
      }
    /**
     * Sets the value of a given index to a given value.
     *  If the row or column are in the matrix bounds the  value is changed.
    * @param row the row number
     * @param column the column number
     * @param value the new value
     */
    public void setElement(int row, int column, double value){
        if(isValid(row,column))
             _elements[row][column] = value;
    }
    
    /**
     *Creates a string representation of the matrix.
     * @return a String representation of this matrix
     */
    public String toString(){
        String  buffer = "";
        for (int i = 0; i < getNumberOfRows(); i++){
            for (int j = 0; j < getNumberOfColumns(); j++){
                buffer = buffer + getElement(i,j) + "\t";
            }
            buffer += "\n";
        }
        return buffer;          
    }
    
    /**
     * Checks if the value of a given index is Maximum in its row and in its column . If row or column not valid False is returned.
     * 
     * @param row the row number
     * @param col the column number
     * 
     * @return True if the value of a given index is Maximum in its row and in its column otherwise False. If row or column not valid False is returned.
     */
    public boolean isMax (int row, int col)
    {                                                                                  
        int _row = row, _col = col;
        _numOfRows = getNumberOfRows();
        _numOfCols = getNumberOfColumns();
        double _max = 0, _tester;
        
        if (isValid(_row , _col) )
        {
            for (int _x = 0 ; _x < _numOfRows; _x++)  
            {
                _tester = getElement (_x, _col);
                if (_tester > _max)
                        _max = _tester;
            }
            for (int _y = 0; _y < _numOfCols; _y++) 
            {
                _tester = getElement (_row, _y);
                if (_tester > _max)
                    _max = _tester;
            }
            if (_max == getElement (_row, _col) )   
                return true;
             else
                return false;
        }
        else
            return false;
    }   
    
    /**
     * Checks if the values in all rows represent arithmetic sequences.
     * 
     * @return True if the values in all rows represent arithmetic sequences otherwise False.
     */
    public boolean isMathSeq ()   
    {                                                             
        _numOfRows = getNumberOfRows();
        _numOfCols = getNumberOfColumns();
        double _seq1, _seq2;
        boolean _flag = true;
                
        if (_numOfRows == 1 || _numOfCols == 2)
            return _flag;
        for ( int _row = 0; _row < _numOfRows; _row++)
        {
            for (int _col = 2; _col < _numOfCols; _col++ )
            {
                _seq1 = (getElement (_row, _col) )  -  (getElement (_row, _col - 1) );
                _seq2 = (getElement (_row, _col - 1) )  -  (getElement (_row, _col - 2) );
                if (_seq1 != _seq2)
                   _flag = false;
            }
        }
            return _flag;
    }
    
    /**
     * Checks if the value of a given index is balanced (the sum of column num equals the sum of row num). If not balanced, or num is not a valid row and column or matrix is not squared False is returned.
     * 
     * @num
     * 
     * @return True if the value of a given index is balanced. If not balanced or num is not a valid row or column or matrix is not squared False is returned.
     */
    public boolean isBalanced (int num)
    {
        int _num = num;
        if (isValid (_num, _num) == true)
            return colAndRowEqual(_num);
        else
            return false;
    }
    
    //Get a number and calculate this row and this column.
    //Return true if equal and false otherwise.
    private boolean colAndRowEqual(int num)         
    {                                                                                               
        int _num = num;
        _numOfRows = getNumberOfRows();
        _numOfCols = getNumberOfColumns();
                
        if (_numOfCols == _numOfRows )
        {
            if (colAmount(_num) == rowAmount(_num))
                return true;
             else
                return false;
        }
        else
            return false;
    }
    
    
    //Get a column number and calculate all of it's numbers.
    //Return the total amount.
    private double colAmount(int num)                     
    {
        int _num = num;
        _numOfRows = getNumberOfRows();
        double _colAmount = 0;
       for (int _row = 0; _row < _numOfRows;  _row++)
            _colAmount = _colAmount + getElement (_row, _num);
        return _colAmount;
    }
    
    
    //Get a row number and calculate all of it's numbers.
    //Return the total amount.
    private double rowAmount(int num)                   
    {
        int _num = num;
        _numOfCols = getNumberOfColumns();
        double _rowAmount = 0;
        for (int _col = 0; _col < _numOfCols;  _col++)
            _rowAmount = _rowAmount + getElement (_num, _col);
            return _rowAmount;
    }    
    
    
    /**
     * Checks if the matrix is Completely balanced ( for any X in the Matrix range the sum of column X equals the sum of row X).
     * 
     * @return True if the matrix is Completely balanced. If matrix is not squared or the matrix is not completely balanced return False.
     */
    public boolean isCompleteBalanced ()                               
    {                                                                                               
        _numOfRows = getNumberOfRows();
        _numOfCols = getNumberOfColumns();
        boolean _flag = true;

        for (int _x = 0;_x < _numOfRows || _x < _numOfCols; _x++)
            {
            if (colAndRowEqual(_x) != true)
                _flag = false;
            }
            return _flag;
    }

    /**
     * Checks how Many Sub Special matrices are in the matrix.
     * 
     * @return how Many Sub Special matrices are in the matrix.
     */
   public int howManySubSpecial ()
   {
        _numOfRows = getNumberOfRows();
        _numOfCols = getNumberOfColumns();
       double _sum = 0;
       int _subCounter = 0;
       
        for (int _row = 0; _row < _numOfRows - 1; _row++)
        {
            for (int _col = 1; _col < _numOfCols; _col++)
            {
                double _index = getElement (_row, _col);
                for (int _subRow = _row + 1; _subRow < _numOfRows; _subRow++)
                {
                    for (int _subCol = _col - 1; _subCol >= 0; _subCol--)
                    {
                        _sum = _sum + getElement (_subRow, _subCol);
                    }
                }
                if (_sum == _index)
                {
                    _subCounter++;
                    _sum = 0;
                }
                else
                    _sum = 0;
            }
        }   
        return _subCounter;
   }
   
   //Get a row and column index from the edges of the Matrix and check if at least two neighbors are positive.
   //If at least two are positive - update the _matrixSign.
   private void updateEdgeIndexes (int row, int col)
   {
        Matrix _copyElements = new Matrix (_elements);
        int _col = col, _row = row, _counter = 0;
        double _index = _copyElements.getElement (_row, _col);
        
        if (_row == 0)
        {
            
            if (_col == 0)
            {
                for (int _x = _row + 1, _y = 0; _y <= 1; _y++)
                {
                    double _neighbor = _copyElements.getElement (_x, _y);
                    if (_neighbor > 0)
                        _counter ++;
                }
                double _neighbor = _copyElements.getElement (_row, _col + 1);
                if (_neighbor > 0)
                    _counter ++;
            }
            
            else if (_col == _numOfCols - 1 )
            {
                for (int _x = _row + 1, _y = _numOfCols - 1; _y >= _numOfCols -2 ; _y--)
                {
                    double _neighbor = _copyElements.getElement (_x, _y);
                    if (_neighbor > 0)
                        _counter ++;
                }
                double _neighbor = _copyElements.getElement (_numOfRows - 2, _col - 1);
                if (_neighbor > 0)
                    _counter ++;
            }
            
            else
                {
                    for (int _x = 0; _x <= 1; _x++)
                    {
                        for (int _y = _col - 1; _y <= _col + 1; _y++)
                        {
                            if (! (_x == _row && _y == _col) )
                            {
                                double _neighbor = _copyElements.getElement (_x, _y);
                                if (_neighbor > 0)
                                    _counter++;
                            }
                        }
                    }
                }
        }
        
        
        else if (_row == _numOfRows - 1)
        {
            
            if (_col == 0)
            {
                for (int _x = _row - 1, _y = 0; _y <= 1; _y++)
                {
                    double _neighbor = _copyElements.getElement (_x, _y);
                    if (_neighbor > 0)
                        _counter ++;
                }
                double _neighbor = _copyElements.getElement (_row, _col + 1);
                if (_neighbor > 0)
                    _counter ++;
            }
            
            else if (_col == _numOfCols - 1 )
            {
                for (int _x = _row - 1, _y = _numOfCols - 1; _y >= _numOfCols - 2 ; _y--)
                {
                    double _neighbor = _copyElements.getElement (_x, _y);
                    if (_neighbor > 0)
                        _counter ++;
                }
                double _neighbor = _copyElements.getElement (_row - 1, _col - 1 );
                if (_neighbor > 0)
                    _counter ++;
            }
            
            else
            {
                for (int _x = _row - 1; _x <= _row; _x++)
                 {
                    for (int _y = _col - 1; _y <= _col + 1; _y++)
                    {
                        if (! (_x == _row && _y == _col) )
                        {
                            double _neighbor = _copyElements.getElement (_x, _y);
                            if (_neighbor > 0)
                            _counter++;
                        }
                    }
                 }
            }
        }
        
        else if (_col == 0)
        {
            for (int _x = _row - 1; _x <= _row + 1; _x++)
                 {
                    for (int _y = _col; _y <= _col + 1; _y++)
                    {
                        if (! (_x == _row && _y == _col) )
                        {
                            double _neighbor = _copyElements.getElement (_x, _y);
                            if (_neighbor > 0)
                            _counter++;
                        }
                    }
                 }
        }

        else if (_col == _numOfCols - 1)
        {
            for (int _x = _row - 1; _x <= _row + 1; _x++)
                 {
                    for (int _y = _col; _y >= _col - 1; _y--)
                    {
                        if (! (_x == _row && _y == _col) )
                        {
                            double _neighbor = _copyElements.getElement (_x, _y);
                            if (_neighbor > 0)
                            _counter++;
                        }
                    }
                 }
        }        
        
        if (_counter >= 2)
        {
            _index = Math.abs (_index);
            _matrixSign.setElement (_row, _col, _index);
        }
        return;
   }   
       
   //Look for negative numbers in the Matix.
   private void findIndexes ()
   {     
        _numOfRows = getNumberOfRows();
        _numOfCols = getNumberOfColumns();
       Matrix _copyElements = new Matrix (_elements);
       
       for (int _row = 0; _row < _numOfRows; _row++)
       {
           for (int _col = 0; _col < _numOfCols; _col++)
           {
               double _index = _copyElements.getElement (_row, _col);
               if (_index < 0)
               {
                   if (_row == 0 || _col == 0 || _row == _numOfRows - 1 || _col == _numOfCols - 1)
                        updateEdgeIndexes (_row, _col);
                   else
                        updateCenterIndexes(_row, _col);
               }
           }
       }
       return;
   }
   
 
   //Get a row and column index from the center of the Matrix and check if at least two neighbors are positive.
   //If at least two are positive - update the _matrixSign.
  private void updateCenterIndexes (int row, int col)
  {
      int _row = row, _col = col;
        _numOfRows = getNumberOfRows();
        _numOfCols = getNumberOfColumns();
        
        Matrix _copyElements = new Matrix (_elements);
        
        int _counter = 0;
        
        for (int _x = _row - 1; _x <= _row + 1; _x++)
        {
            for (int _y = _col - 1; _y <= _col + 1; _y++)
            {
                if (! (_x == _row && _y == _col) )
                {
                    double _neighbor = _copyElements.getElement (_x, _y);
                    if (_neighbor > 0 ) 
                        _counter++;
                }
            }
        }
        if (_counter >= 2)
        {
            double _index = _copyElements.getElement (_row, _col);
            _index = Math.abs (_index);
            _matrixSign.setElement (_row, _col, _index);
        }
        return;
  }
    
  /**
   * Returns a new "signed" matrix. Any negative elements are made positive if they have at least 2 positive neighbors.
   * 
   * @return new Matrix , a copy of the original Matrix after being "signed".
   */
  public Matrix sign ()
  {
       _matrixSign = new Matrix (_elements);
      findIndexes ();
      return _matrixSign;
  }
  
  //Get parameters and check if the given number is in the array using binary search
  private static boolean findNum (int[][] _mat, int _topRow, int _bottomRow, int _topCol, int _bottomCol, int x)
  {
      boolean _found = false;
        
      //Making sure the number is in the matrix
      if (x >= _mat [_bottomRow] [_bottomCol] && x <= _mat [_topRow] [_topCol])
      {
        while (_topCol >= _bottomCol && _topRow >= _bottomRow && _found != true)
        {  
           int _midCol = (_topCol + _bottomCol) / 2;
           int _midRow = (_topRow + _bottomRow) / 2;
           
           //Making sure the top and bottom are not the same
           if ( !(_topRow == _bottomRow && _topCol == _bottomCol))
           {
               //If it's in the first or second sub matrixes
              if (x <= _mat [_midRow] [_topCol])
              {
                  //SubMatrix number 1
                  if (x <= _mat [_midRow] [_midCol])
                  {
                      _topCol = _midCol;
                      _topRow = _midRow;
                    }
                    //SubMatrix number 2
                    else
                    {
                        _bottomCol = _midCol + 1;
                        _topRow = _midRow;  
                    }
                }
                //If it's in the third of forth sub matrixes
                else
                {
                    //SubMatrix number 3
                    if (x <= _mat [_bottomRow] [_midCol])
                    {
                        _topCol = _midCol;
                        _bottomRow = _midRow + 1;      
                    }
                    //SubMatrix number 4
                    else
                    {
                        _bottomCol = _midCol + 1;
                        _bottomRow = _midRow + 1;
                    }
                }
                
          }
          //If topRow == bottomRow && topCol == bottomCol
          else
          {
            if (_mat [_topRow] [_topCol] == x) 
                return true;
            else
                return false;
            }
        }
     }
      return _found;
  }// SHAY: Very good!
  
  
      
  /**
   * Look for the given number in the given array and reatun true if the number is in the array and false otherwise
   * 
   * @param mat an array
   * @param x a number
   * 
   * @return true if the number is in the array and false otherwise
   */
  //In the worst case it will cost O(logN).// SHAY: Correct
  //For that to happen the number need to be in the last place it's looking for - in the middle of the last quater.
  public static boolean find(int[][] mat, int x) 
  {
      int _midRow = mat.length / 2, _midCol = mat[0].length / 2;
      int _topRow = mat.length - 1, _topCol = mat[0].length - 1, _bottomRow = 0, _bottomCol = 0;
            
      return findNum ( mat, _topRow, _bottomRow, _topCol, _bottomCol, x);
  }
 
 
 
      }