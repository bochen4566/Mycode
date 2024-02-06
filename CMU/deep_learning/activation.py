import numpy as np
import scipy
import math

class Identity:

    def forward(self, Z):

        self.A = Z

        return self.A

    def backward(self, dLdA):

        dAdZ = np.ones(self.A.shape, dtype="f")
        dLdZ = dLdA * dAdZ

        return dLdZ


class Sigmoid:
    """
    On same lines as above:
    Define 'forward' function
    Define 'backward' function
    Read the writeup for further details on Sigmoid.
    """
    def forward(self, Z):

        A = 1 / (1 + np.exp(-Z))
        self.A = A
        return A

    def backward(self, dLdA):

        
        dLdZ = dLdA * (self.A - self.A*self.A)
        self.dLdZ = dLdZ
        return dLdZ


class Tanh:
    """
    On same lines as above:
    Define 'forward' function
    Define 'backward' function
    Read the writeup for further details on Tanh.
    """
    def forward(self, Z):

        A = np.tanh(Z)
        self.A = A
        return A

    def backward(self, dLdA):

        self.Ones = np.ones(self.A.shape, dtype="f")
        dLdZ = dLdA * (self.Ones - self.A*self.A)
        self.dLdZ = dLdZ
        return dLdZ


class ReLU:
    """
    On same lines as above:
    Define 'forward' function
    Define 'backward' function
    Read the writeup for further details on ReLU.
    """
    def forward(self, Z):
        A = np.where(Z > 0, Z, 0)
        self.A = A
        return A

    def backward(self, dLdA):

        dLdZ = np.where(self.A > 0, dLdA, 0)
        self.dLdZ = dLdZ
        return dLdZ
        

class GELU:
    """
    On same lines as above:
    Define 'forward' function
    Define 'backward' function
    Read the writeup for further details on GELU.
    """
    def forward(self, Z):
        self.Z = Z
        A = 0.5 * Z * (1 + scipy.special.erf(Z / np.sqrt(2)))
        self.A = A
        return A

    def backward(self, dLdA):

        dLdZ = dLdA * (0.5 * (1 + scipy.special.erf(self.Z / np.sqrt(2))) + 0.5 * self.Z * (2 / np.sqrt(2 * math.pi)) * np.exp(-0.5 * self.Z**2))
        self.dLdZ = dLdZ
        return dLdZ

class Softmax:
    """
    On same lines as above:
    Define 'forward' function
    Define 'backward' function
    Read the writeup for further details on Softmax.
    """

    def forward(self, Z):
        """
        Remember that Softmax does not act element-wise.
        It will use an entire row of Z to compute an output element.
        """
        shiftZ = Z - np.max(Z, axis=1, keepdims=True)
        expZ = np.exp(shiftZ)
        A = expZ / np.sum(expZ, axis=1, keepdims=True)
        self.A = A
        return A
        
    
    def backward(self, dLdA):
        N, C = dLdA.shape  # Batch size and number of features
        dLdZ = np.zeros_like(dLdA)

        for i in range(N):
            # Initialize the Jacobian matrix for the i-th output
            J = np.zeros((C, C))

            # Compute the Jacobian matrix
            for m in range(C):
                for n in range(C):
                    if m == n:
                        J[m, n] = self.A[i, m] * (1 - self.A[i, m])
                    else:
                        J[m, n] = -self.A[i, m] * self.A[i, n]

            # Compute the gradient of the loss with respect to the i-th input
            dLdZ[i, :] = np.dot(J, dLdA[i, :])

        return dLdZ
    