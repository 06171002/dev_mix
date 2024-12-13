<template>
  <div class="m-auto w-full">
    <div class="px-2">
      <div class="flex flex-col space-x-1 space-y-4">
        <!-- 전체 선택 버튼 -->
        <div class="flex justify-between">
          <button type="button" class="border whitespace-nowrap rounded-full text-sm hover:bg-gray-200 px-2 py-1" @click="toggleAllCheckboxes">전체</button>
          <button type="button" class="border whitespace-nowrap rounded-full text-sm hover:bg-gray-200 px-2 py-1" @click="deleteSelectedComments">삭제</button>
        </div>

        <!-- 댓글이 없을 경우 -->
        <div v-if="commentsarr.length === 0" class="text-center text-gray-500 py-8" style="height: 500px">작성한 댓글이 없습니다.</div>

        <!-- 내가 작성한 댓글 내용 반복 -->
        <div v-for="(comment, index) in commentsarr" :key="comment.id" class="flex flex-col space-y-1">
          <div class="flex items-center justify-between w-full">
            <div class="flex gap-2 items-center w-full">
              <!-- 각 체크박스의 상태를 v-model로 바인딩 -->
              <input type="checkbox" v-model="checkboxes[index]" class="form-checkbox w-4 h-4 rounded-lg border-gray-300 text-[#7371fc] focus:ring-[#7371fc]" />

              <!-- 댓글내용 텍스트, 말줄임표 적용 -->
              <div class="flex flex-col w-full">
                <RouterLink :to="`/projectview/${comment.boardId}`">
                  <p class="text-gray-700 w-full truncate max-w-[500px] whitespace-nowrap overflow-hidden">
                    {{ comment.content }}
                  </p>
                  <p class="text-sm text-gray-500">{{ comment.boardTitle }}</p>
                </RouterLink>
              </div>
            </div>

            <!-- 작성일, 수정, 삭제를 오른쪽 끝에 위치시키기 -->
            <div class="flex gap-3 text-center justify-end items-center text-sm">
              <div class="text-sm text-gray-500 flex-shrink-0">{{ comment.createdAt }}</div>
              <p class="flex-shrink-0 text-gray-500 cursor-pointer hover:text-gray-800">수정</p>
              <p class="flex-shrink-0 text-gray-500 cursor-pointer hover:text-gray-800" @click="commentDelete(comment.commentId)">삭제</p>
            </div>
          </div>
          <div class="mt-2">
            <hr class="border-t border-gray-300 mt-2 mb-2" />
          </div>
        </div>
        <!-- 내가 작성한 댓글 내용 반복 끝 -->
      </div>
    </div>
  </div>
</template>

<script setup>
import { deleteComments, getCommentsView } from '@/api/projectApi';
import { usercomments } from '@/api/userApi';
import { useUserStore } from '@/store/userStore';
import { ref, watch, watchEffect } from 'vue';

const commentsarr = ref([]);

// user_id 가져오기
const useStore = useUserStore();

const mycomments = async () => {
  try {
    const res = await usercomments(useStore.userId);
    console.log('댓글 목록: ', res);

    // 데이터 구조 확인 후, commentsarr에 할당
    if (Array.isArray(res.result)) {
      commentsarr.value = res.result;
    } else {
      console.error('res, data, result 확인해보기: ', res);
    }
  } catch (error) {
    console.error('내가 작성한 게시물 가져오기 에러: ', error);
  }
};

// 댓글 삭제 (개별)
const commentDelete = async (id) => {
  const comment = commentsarr.value.find((comment) => comment.id === id);

  if (!comment) {
    console.error('댓글을 찾을 수 없습니다.');
    return;
  }

  const boardId = comment.boardId;
  const isConfirmed = window.confirm('댓글을 삭제 하시겠습니까?');

  if (isConfirmed) {
    try {
      const res = await deleteComments(boardId, id);
      if (res.status === 200) {
        alert('댓글이 정상적으로 삭제되었습니다.');
        const updatedComments = await usercomments(useStore.userId);
        if (updatedComments.status === 200) {
          commentsarr.value = updatedComments.data.result; // 댓글 목록 갱신
        }
      } else {
        alert('에러: ' + res.data);
      }
    } catch (error) {
      console.error('삭제 중 오류 발생:', error);
      alert('삭제 중 오류가 발생했습니다.');
    }
  }
};

// 체크박스 상태를 관리하는 배열 (댓글 수만큼 체크박스)
const checkboxes = ref([]);

// 전체 선택 토글 함수
const toggleAllCheckboxes = () => {
  const allChecked = checkboxes.value.every((checkbox) => checkbox); // 모든 체크박스가 선택된 상태인지 확인
  checkboxes.value = checkboxes.value.map(() => !allChecked); // 모든 체크박스를 반전시킴
};

// 선택된 댓글 삭제
const deleteSelectedComments = async () => {
  const selectedComments = commentsarr.value.filter((_, index) => checkboxes.value[index]); // 체크된 댓글만 필터링
  if (selectedComments.length === 0) {
    alert('삭제할 댓글을 선택해주세요.');
    return;
  }

  const isConfirmed = window.confirm(`선택한 ${selectedComments.length}개의 댓글을 삭제하시겠습니까?`);
  if (isConfirmed) {
    try {
      for (const comment of selectedComments) {
        const res = await deleteComments(comment.boardId, comment.id);
        if (res.status === 200) {
          alert('댓글이 정상적으로 삭제되었습니다.');
        } else {
          alert('에러: ' + res.data);
        }
      }

      // 삭제 후 댓글 목록 갱신
      const updatedComments = await getCommentsView(selectedComments[0].boardId);
      if (updatedComments.status === 200) {
        commentsarr.value = updatedComments.data.result; // 댓글 목록 갱신
        checkboxes.value = new Array(commentsarr.value.length).fill(false); // 체크박스 초기화
      }
    } catch (error) {
      console.error('삭제 중 오류 발생:', error);
      alert('삭제 중 오류가 발생했습니다.');
    }
  }
};

// commentsarr 변경 시 checkboxes 배열 동기화
watch(
  commentsarr,
  (newComments) => {
    checkboxes.value = new Array(newComments.length).fill(false); // 새로운 댓글 배열 길이에 맞게 체크박스 배열 초기화
  },
  { immediate: true }
);

watchEffect(() => {
  mycomments();
});
</script>

<style scoped></style>
