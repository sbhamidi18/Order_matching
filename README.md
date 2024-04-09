README

**Author**: Sandhya Sirisha Bhamidipati

**Order**: Handles requests to buy or sell financial instruments, storing essential details like quantity, price, and order type for smooth execution in trading systems.

**OrderBook**: Organizes buy and sell orders based on price levels, facilitating efficient order matching and execution to maintain market liquidity and optimize trading strategies.

**InputProcessor**: Acts as a bridge between external data sources and the software system, ensuring accurate parsing, validation, and transformation of input data to maintain data integrity and reliability.

**Trade**: Tracks transaction details in financial markets, including asset type, quantity, price, and timestamp, enabling comprehensive record-keeping and insightful analysis of trading activities.

**OutputProcessorAndFormatter**: Refines system data to meet specified standards, ensuring consistency and readability across various outputs such as user interfaces or external files, enhancing user experience and facilitating data interpretation.

**Testing**: Evaluates the functionality and performance of the system through various scenarios and inputs, ensuring correct behavior and identifying potential issues for refinement and improvement.

**input.txt** contains a sample input for the user.

To **compile**: javac Order.java OrderBook.java Trade.java InputProcessor.java OutputProcessorAndFormatter.java Testing.java

To **run**: java Testing (Testing class contains the main method in this scenario)