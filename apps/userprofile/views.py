from .models import User
from django.http import Http404
from rest_framework import generics, status
from rest_framework.response import Response
from rest_framework.views import APIView

from .serializers import UserSerializerPublic, UserSerializerPrivate
from .permissions import IsOwnerOrAdmin, IsAuthOrPost


class UserView(generics.ListCreateAPIView):
    """
    Returns a list of all users
    """
    queryset = User.objects.all()
    permission_classes = [IsAuthOrPost]

    def get_serializer_class(self):
        if self.request.user.is_staff or (self.request.method == "POST"):
            return UserSerializerPrivate
        return UserSerializerPublic



class UserDetail(APIView):
    """
    GET, POST and PUT userinfo, resticted if request.userprofile is not admin or self
    """

    permission_classes = [IsOwnerOrAdmin]

    def get_object(self, pk):
        try:
            return User.objects.get(pk=pk)
        except User.DoesNotExist:
            raise Http404

    def get(self, request, pk, format=None):
        user = self.get_object(pk)
        serializer = UserSerializerPrivate(user)
        return Response(serializer.data)

    def put(self, request, pk, format=None):
        user = self.get_object(pk)
        serializer  = UserSerializerPrivate(user, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk):
        user = self.get_object(pk)
        user.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)
