import numpy as np


class MSELoss:

    def forward(self, A, Y):
        """
        Calculate the Mean Squared error
        :param A: Output of the model of shape (N, C)
        :param Y: Ground-truth values of shape (N, C)
        :Return: MSE Loss(scalar)

        """

        self.A = A
        self.Y = Y
        self.N = A.shape[0]  # TODO
        self.C = A.shape[1]  # TODO
        se = (A-Y)*(A-Y)  # TODO
        sse = np.ones((self.N,1)).T.dot(se).dot(np.ones((self.C,1)))  # TODO
        mse = sse/(self.N*self.C)  # TODO

        return mse

    def backward(self):

        dLdA = 2*(self.A-self.Y)/(self.N*self.C)

        return dLdA


class CrossEntropyLoss:

    def forward(self, A, Y):
        """
        Calculate the Cross Entropy Loss
        :param A: Output of the model of shape (N, C)
        :param Y: Ground-truth values of shape (N, C)
        :Return: CrossEntropyLoss(scalar)

        Refer the the writeup to determine the shapes of all the variables.
        Use dtype ='f' whenever initializing with np.zeros()
        """
        self.A = A
        self.Y = Y
        N = A.shape[0]  # TODO
        C = Y.shape[1]  # TODO
        

        Ones_C = np.ones((C,1), dtype='f')  # TODO
        Ones_N = np.ones((N,1), dtype='f')  # TODO
        shiftA = A - np.max(A, axis=1, keepdims=True)
        expA = np.exp(shiftA)
        self.softmax = expA / np.sum(expA, axis=1, keepdims=True)  # TODO
        crossentropy = (-Y*np.log(self.softmax)).dot(Ones_C)  # TODO
        sum_crossentropy = Ones_N.T.dot(crossentropy)  # TODO
        L = sum_crossentropy / N

        return L

    def backward(self):

        dLdA = (self.softmax - self.Y) / self.Y.shape[0] # TODO

        return dLdA
